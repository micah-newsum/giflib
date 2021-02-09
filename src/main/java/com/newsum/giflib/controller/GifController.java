package com.newsum.giflib.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class GifController
{
    @RequestMapping("/")
    public String listGifs()
    {
        return "home";
    }
}
