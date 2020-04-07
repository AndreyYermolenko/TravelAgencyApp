package com.yermolenko.controllers;

import com.yermolenko.model.SearchTourParams;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    @GetMapping("/allTours")
    public String getAllTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());
        return "allTours";
    }

    @PostMapping("/allTours")
    public String getAllTours(@ModelAttribute SearchTourParams tourParams) {
        System.out.println(tourParams.toString());
        return "allTours";
    }

    @GetMapping("/")
    public String view() {
        return "welcome";
    }



//    @GetMapping("/test")
//    public String test(Model model) {
//        return "test";
//    }
//
//    @PostMapping("/test")
//    public String test(@ModelAttribute SearchTourParams tourParams) {
//        return "test";
//    }

}
