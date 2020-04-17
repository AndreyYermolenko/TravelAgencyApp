package com.yermolenko.controllers;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.TravelTourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;

@Controller
public class MainController {

    private final TravelTourService travelTourService;

    public MainController(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
    }


    @GetMapping("/tours")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());

        return "tours";
    }

    @PostMapping("/tours")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(HttpSession session,
                           @ModelAttribute SearchTourParams tourParams,
                              Model model) {
        System.out.println(tourParams.toString());

        session.setAttribute("includedPage", "showToursForUser.jsp");
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();
        for(GrantedAuthority authority: authorities) {
            if ("manager".equals(authority.getAuthority())) {
                session.setAttribute("includedPage", "showToursForManager.jsp");
            }
        }

        List<TravelTour> tours = travelTourService.getTours(tourParams);
        model.addAttribute("tours", tours);

        return "tours";
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

        return "tours";
    }

    @GetMapping("/deleteTour")
    @PreAuthorize("hasAuthority('manager')")
    public String deleteTour(@RequestParam int id) {
        travelTourService.deleteTour(id);

        return "tours";
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

        return "tours";
    }

    @GetMapping("/reservationTour")
    @PreAuthorize("hasAuthority('user')")
    public String reservationTour() {

        return "reservationTour";
    }

}
