package com.yermolenko.controllers;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.Service;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final Service service;

    public MainController(Service service) {
        this.service = service;
    }

    @GetMapping("/tours")
    public String getTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());

        return "tours";
    }

    @PostMapping("/tours")
    public String getTours(@ModelAttribute SearchTourParams tourParams,
                              Model model) {
        List<TravelTour> tours = service.getTours(tourParams);
        model.addAttribute("tours", tours);
        model.addAttribute("includedPage", "showToursForManager.jsp"); //showToursForUser.jsp

        return "tours";
    }

    @GetMapping("/updateTour")
    public String updateTour(Model model,
                             @RequestParam int id) {
        TravelTour tour = service.getTour(id);
        model.addAttribute("tourCurrent", tour);
        model.addAttribute("tourUpdate", new TravelTour());

        return "updateTour";
    }

    @PostMapping("/updateTour")
    public String updateTour(@ModelAttribute TravelTour tourUpdate,
                             @RequestParam int id) {
        service.updateTour(id, tourUpdate);

        return "redirect:tours";
    }

    @GetMapping("/deleteTour")
    public String deleteTour(@RequestParam int id) {
        service.deleteTour(id);

        return "redirect:tours";
    }

    @GetMapping("/addTour")
    public String addTour(Model model) {
        model.addAttribute("newTour", new TravelTour());

        return "addTour";
    }

    @PostMapping("/addTour")
    public String addTour(@ModelAttribute TravelTour tour) {
        service.addTour(tour);

        return "redirect:tours";
    }

    @GetMapping("/reservationTour")
    public String reservationTour() {

        return "reservationTour";
    }

    @GetMapping("/")
    public String view() {
        return "login";
    }

}
