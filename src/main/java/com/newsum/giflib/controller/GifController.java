package com.newsum.giflib.controller;

import com.newsum.giflib.data.GifRepository;
import com.newsum.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.util.List;

@Controller
public class GifController
{
    @Autowired
    private GifRepository gifRepository;

    @RequestMapping("/")
    public String listGifs(ModelMap modelMap)
    {
        List<Gif> allGifs = gifRepository.getAllGifs();
        modelMap.put("gifs",allGifs);
        return "home";
    }

    @RequestMapping("/search")
    public String listGifs(@RequestParam String name, ModelMap modelMap)
    {
        List<Gif> allGifs = gifRepository.findAllByName(name);
        modelMap.put("gifs",allGifs);
        return "home";
    }

    @RequestMapping("/gif/{name}")
    public String gifDetails(@PathVariable String name, ModelMap modelMap)
    {
        Gif gif = gifRepository.findByName(name);
        modelMap.put("gif",gif);
        return "gif-details";
    }

    @RequestMapping("/favorites")
    public String favorites(ModelMap modelMap)
    {
        List<Gif> gifs = gifRepository.getAllFavorites();
        modelMap.put("gifs",gifs);
        return "favorites";
    }
}
