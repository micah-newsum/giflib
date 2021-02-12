package com.newsum.giflib.web.controller;

import com.newsum.giflib.model.Gif;
import com.newsum.giflib.service.CategoryService;
import com.newsum.giflib.service.GifService;
import com.newsum.giflib.web.FlashMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class GifController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private GifService gifService;

    // Home page - index of all GIFs
    @RequestMapping("/")
    public String listGifs(Model model) {
        // Get all gifs
        List<Gif> gifs = gifService.findAll();

        model.addAttribute("gifs", gifs);
        return "gif/index";
    }

    // Single GIF page
    @RequestMapping("/gifs/{gifId}")
    public String gifDetails(@PathVariable Long gifId, Model model) {
        // Get gif whose id is gifId
        Gif gif = gifService.findById(gifId);

        model.addAttribute("gif", gif);
        return "gif/details";
    }

    // GIF image data
    @RequestMapping("/gifs/{gifId}.gif")
    @ResponseBody
    public byte[] gifImage(@PathVariable Long gifId) {
        // Return image data as byte array of the GIF whose id is gifId
        Gif gif = gifService.findById(gifId);
        return gif.getBytes();
    }

    // Favorites - index of all GIFs marked favorite
    @RequestMapping("/favorites")
    public String favorites(Model model) {
        // Get list of all GIFs marked as favorite
        List<Gif> faves = gifService.findAllFavorites();

        model.addAttribute("gifs",faves);
        model.addAttribute("username","Chris Ramacciotti"); // Static username
        return "gif/favorites";
    }

    // Upload a new GIF
    @RequestMapping(value = "/gifs", method = RequestMethod.POST)
    public String addGif(Gif gif, @RequestParam MultipartFile file, RedirectAttributes redirectAttributes) {
        // Upload new GIF if data is valid
        gifService.save(gif,file);

        // Add a flash message for success
        redirectAttributes.addFlashAttribute(new FlashMessage("Gif successfully uploaded!",FlashMessage.Status.SUCCESS));

        // Redirect browser to new GIF's detail view
        return String.format("redirect:/gifs/%s",gif.getId());
    }

    // Form for uploading a new GIF
    @RequestMapping("/upload")
    public String formNewGif(Model model) {
        // Add model attributes needed for new GIF upload form
        if (!model.containsAttribute("gif"))
        {
            model.addAttribute("gif",new Gif());
        }
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("action","/gifs");
        model.addAttribute("heading","Upload");
        model.addAttribute("submit","Add");

        return "gif/form";
    }

    // Form for editing an existing GIF
    @RequestMapping(value = "/gifs/{gifId}/edit")
    public String formEditGif(@PathVariable Long gifId, Model model) {
        // Add model attributes needed for edit form
        model.addAttribute("gif",gifService.findById(gifId));
        model.addAttribute("action","edit");
        model.addAttribute("categories",categoryService.findAll());
        model.addAttribute("action",String.format("/gifs/%s",gifId));
        model.addAttribute("heading","Edit GIF");
        model.addAttribute("submit","Update");

        return "gif/form";
    }

    // Update an existing GIF
    @RequestMapping(value = "/gifs/{gifId}", method = RequestMethod.POST)
    public String updateGif(@Valid Gif gif, BindingResult result, RedirectAttributes redirectAttributes, MultipartFile multipartFile) {
        // Update GIF if data is valid
        if (result.hasErrors())
        {
            // Include validation errors upon redirect
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.category",result);

            // Add flash Category model if invalid
            redirectAttributes.addFlashAttribute(gif);

            // Redirect back to the form
            return String.format("redirect:/gifs/%s/edit",gif.getId());
        }

        gifService.save(gif,multipartFile);

        redirectAttributes.addFlashAttribute(new FlashMessage("Gif successfully updated!",FlashMessage.Status.SUCCESS));

        // Redirect browser to updated GIF's detail view
        return "redirect:/gif/index";
    }

    // Delete an existing GIF
    @RequestMapping(value = "/gifs/{gifId}/delete", method = RequestMethod.POST)
    public String deleteGif(@PathVariable Long gifId, RedirectAttributes redirectAttributes) {
        // Delete the GIF whose id is gifId
        Gif gif = gifService.findById(gifId);
        gifService.delete(gif);
        redirectAttributes.addFlashAttribute("flash",new FlashMessage("GIF deleted successfully!", FlashMessage.Status.SUCCESS));
        // Redirect to app root
        return "redirect:/";
    }

    // Mark/unmark an existing GIF as a favorite
    @RequestMapping(value = "/gifs/{gifId}/favorite", method = RequestMethod.POST)
    public String toggleFavorite(@PathVariable Long gifId, Model model, HttpServletRequest req)
    {
        // With GIF whose id is gifId, toggle the favorite field
        Gif gif = gifService.findById(gifId);
        gifService.toggleFavorite(gif);
        model.addAttribute("gif",gif);

        // Redirect to GIF's detail view
        return String.format("redirect:%s",req.getHeader("referer"));
    }

    // Search results
    @RequestMapping("/search")
    public String searchResults(@RequestParam String q, Model model) {
        // TODO: Get list of GIFs whose description contains value specified by q
        List<Gif> gifs = new ArrayList<>();

        model.addAttribute("gifs",gifs);
        return "gif/index";
    }
}