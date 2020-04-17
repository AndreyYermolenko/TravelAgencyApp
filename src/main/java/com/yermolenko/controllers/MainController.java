package com.yermolenko.controllers;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.TravelTourService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    private final TravelTourService travelTourService;

    public MainController(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
    }

    @GetMapping("/tours")
    public String getTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());

        return "tours";
    }

    @PostMapping("/tours")
    public String getTours(HttpSession session,
                           @ModelAttribute SearchTourParams tourParams,
                              Model model) {
        System.out.println(tourParams.toString());

        session.setAttribute("includedPage", "showToursForManager.jsp"); //showToursForUser.jsp
        List<TravelTour> tours = travelTourService.getTours(tourParams);
        model.addAttribute("tours", tours);

        return "tours";
    }

    @GetMapping("/updateTour")
    public String updateTour(Model model,
                             @RequestParam int id) {
        TravelTour tour = travelTourService.getTour(id);
        model.addAttribute("tourCurrent", tour);
        model.addAttribute("tourUpdate", new TravelTour());

        return "updateTour";
    }

    @PostMapping("/updateTour")
    public String updateTour(@ModelAttribute TravelTour tourUpdate,
                             @RequestParam int id) {
        travelTourService.updateTour(id, tourUpdate);

        return "tours";
    }

    @GetMapping("/deleteTour")
    public String deleteTour(@RequestParam int id) {
        travelTourService.deleteTour(id);

        return "tours";
    }

    @GetMapping("/addTour")
    public String addTour(Model model) {
        model.addAttribute("newTour", new TravelTour());

        return "addTour";
    }

    @PostMapping("/addTour")
    public String addTour(@ModelAttribute TravelTour tour) {
        travelTourService.addTour(tour);

        return "tours";
    }

    @GetMapping("/reservationTour")
    public String reservationTour() {

        return "reservationTour";
    }

}
