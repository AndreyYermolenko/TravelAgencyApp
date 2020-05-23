package com.yermolenko.controllers;

import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelAgencyService;
import com.yermolenko.services.TravelTourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainControllerForManager {

    private final TravelTourService travelTourService;

    private final TravelAgencyService travelAgencyService;

    public MainControllerForManager(TravelTourService travelTourService, TravelAgencyService travelAgencyService) {
        this.travelTourService = travelTourService;
        this.travelAgencyService = travelAgencyService;
    }

    /**
     * Controller updateTour is responsible for updating tours.
     *
     * @param model of type Model
     * @param id of type int
     * @return String
     */
    @GetMapping("/updateTour")
    @PreAuthorize("hasAuthority('manager')")
    public String updateTour(Model model,
                             @RequestParam int id) {
        TravelTour tour = travelTourService.getTour(id);
        model.addAttribute("tourCurrent", tour);
        model.addAttribute("tourUpdate", new TravelTour());
        model.addAttribute("resorts", travelAgencyService.getResorts());
        model.addAttribute("travelCarriers", travelAgencyService.getTravelCarriers());

        return "updateTour";
    }

    /**
     * Controller updateTour is responsible for updating tours.
     *
     * @param tourUpdate of type TravelTour
     * @param id of type int
     * @return String
     */
    @PostMapping("/updateTour")
    @PreAuthorize("hasAuthority('manager')")
    public String updateTour(@ModelAttribute TravelTour tourUpdate,
                             @RequestParam int id) {
        travelTourService.updateTour(id, tourUpdate);

        return "quickSearchTours";
    }

    /**
     * Controller deleteTour is responsible for deleting tours.
     *
     * @param id of type int
     * @return String
     */
    @GetMapping("/deleteTour")
    @PreAuthorize("hasAuthority('manager')")
    public String deleteTour(@RequestParam int id) {
        travelTourService.deleteTour(id);

        return "quickSearchTours";
    }

    /**
     * Controller addTour is responsible for adding tours.
     *
     * @param model of type Model
     * @return String
     */
    @GetMapping("/addTour")
    @PreAuthorize("hasAuthority('manager')")
    public String addTour(Model model) {
        model.addAttribute("newTour", new TravelTour());
        model.addAttribute("resorts", travelAgencyService.getResorts());
        model.addAttribute("travelCarriers", travelAgencyService.getTravelCarriers());

        return "addTour";
    }

    /**
     * Controller addTour is responsible for adding tours.
     *
     * @param tour of type TravelTour
     * @return String
     */
    @PostMapping("/addTour")
    @PreAuthorize("hasAuthority('manager')")
    public String addTour(@ModelAttribute TravelTour tour) {
        travelTourService.addTour(tour);

        return "quickSearchTours";
    }

    /**
     * Controller listOfReservedTourUsers is responsible for getting list of reserved tour users for manager.
     *
     * @param tourId of type int
     * @param model of type Model
     * @return String
     */
    @GetMapping("/listOfReservedTourUsers")
    @PreAuthorize("hasAuthority('manager')")
    public String listOfReservedTourUsers(@RequestParam(name = "id") int tourId,
                                          Model model) {
        TravelTour tour = new TravelTour();
        tour.setId(tourId);

        List<User> users = travelTourService.getListOfReservedTourUsers(tour);
        model.addAttribute("users", users);

        return "listOfReservedTourUsers";
    }
}
