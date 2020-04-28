package com.yermolenko.controllers;

import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelTourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    private final TravelTourService travelTourService;

    public MainController(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
    }

    @GetMapping("/tours/advancedSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());

        return "advancedSearchTours";
    }

    @PostMapping("/tours/advancedSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(@ModelAttribute SearchTourParams tourParams,
                              Model model) {
        System.out.println(tourParams.toString());

        List<TravelTour> tours = travelTourService.getTours(tourParams);
        model.addAttribute("tours", tours);

        return "advancedSearchTours";
    }

    @RequestMapping("/tours/quickSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getToursQuick(Model model) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();
        if (authorities.contains(new SimpleGrantedAuthority("manager"))) {
            model.addAttribute("role", "manager");
        } else {
            model.addAttribute("role", "user");
        }

        return "quickSearchTours";
    }

    @GetMapping("/showTours")
    @PreAuthorize("hasAuthority('user')")
    public String showTours(@RequestParam(value = "") String destination,
                            Model model) {
        SearchTourParams params = new SearchTourParams();
        params.setDestination(destination);
        List<TravelTour> tours = travelTourService.getTours(params);
        model.addAttribute("tours", tours);

        return "showTours";
    }

    @GetMapping("/updateTour")
    @PreAuthorize("hasAuthority('manager')")
    public String updateTour(Model model,
                             @RequestParam int id) {
        TravelTour tour = travelTourService.getTour(id);
        model.addAttribute("tourCurrent", tour);
        model.addAttribute("tourUpdate", new TravelTour());

        return "updateTour";
    }

    @PostMapping("/updateTour")
    @PreAuthorize("hasAuthority('manager')")
    public String updateTour(@ModelAttribute TravelTour tourUpdate,
                             @RequestParam int id) {
        travelTourService.updateTour(id, tourUpdate);

        return "quickSearchTours";
    }

    @GetMapping("/deleteTour")
    @PreAuthorize("hasAuthority('manager')")
    public String deleteTour(@RequestParam int id) {
        travelTourService.deleteTour(id);

        return "quickSearchTours";
    }

    @GetMapping("/addTour")
    @PreAuthorize("hasAuthority('manager')")
    public String addTour(Model model) {
        model.addAttribute("newTour", new TravelTour());

        return "addTour";
    }

    @PostMapping("/addTour")
    @PreAuthorize("hasAuthority('manager')")
    public String addTour(@ModelAttribute TravelTour tour) {
        travelTourService.addTour(tour);

        return "quickSearchTours";
    }

    @GetMapping("/reservationTour")
    @PreAuthorize("hasAuthority('user')")
    public String reservationTour(HttpSession session,
                                  @RequestParam(name = "id") int idTour) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TravelTour tour = travelTourService.getTour(idTour);
        boolean result = travelTourService.reservationTour(user, tour);
        session.setAttribute("isSuccess", result);
        return "/reservationTour";
    }

    @GetMapping("/tours/reserved")
    @PreAuthorize("hasAuthority('user')")
    public String getReservedTours(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<TravelTour> tours = travelTourService.getReservedTours(user);
        model.addAttribute("tours", tours);

        return "reservedTours";
    }

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
