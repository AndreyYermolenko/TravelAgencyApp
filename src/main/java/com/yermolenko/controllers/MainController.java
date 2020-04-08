package com.yermolenko.controllers;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MainController {

    private final Service service;

    public MainController(Service service) {
        this.service = service;
    }

    @GetMapping("/allTours")
    public String getAllTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());
        return "allTours";
    }

    @PostMapping("/allTours")
    public String getAllTours(@ModelAttribute SearchTourParams tourParams,
                              Model model) {
        List<TravelTour> tours = service.getSomeTours(tourParams);
        model.addAttribute("tours", tours);

        System.out.println(tourParams.toString() + "\n" + tours.toString());
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
