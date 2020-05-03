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

/**
 * Class MainController contains main controllers.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Controller
public class MainController {

    private final TravelTourService travelTourService;

    /**
     * Constructor MainController creates a new MainController instance.
     *
     * @param travelTourService of type TravelTourService
     */
    public MainController(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
    }

    /**
     * Controller getTours is responsible for getting tours.
     *
     * @param model of type Model
     * @return String
     */
    @GetMapping("/tours/advancedSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(Model model) {
        model.addAttribute("searchTourParams", new SearchTourParams());

        return "advancedSearchTours";
    }

    /**
     * Controller getTours is responsible for getting tours.
     *
     * @param tourParams of type SearchTourParams
     * @param model of type Model
     * @return String
     */
    @PostMapping("/tours/advancedSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getTours(@ModelAttribute SearchTourParams tourParams,
                              Model model) {
        List<TravelTour> tours = travelTourService.getTours(tourParams);
        model.addAttribute("tours", tours);

        return "advancedSearchTours";
    }

    /**
     * Controller getToursQuick is responsible for quick search tours.
     *
     * @param model of type Model
     * @return String
     */
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

    /**
     * Controller showTours is responsible for show tours.
     *
     * @param destination of type String
     * @param model of type Model
     * @return String
     */
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
     * Controller reservationTour is responsible for reservation tours for users.
     *
     * @param session of type HttpSession
     * @param idTour of type int
     * @return String
     */
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

    /**
     * Controller getReservedTours is responsible for getting reserved tours for users.
     *
     * @param model of type Model
     * @return String
     */
    @GetMapping("/tours/reserved")
    @PreAuthorize("hasAuthority('user')")
    public String getReservedTours(Model model) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        List<TravelTour> tours = travelTourService.getReservedTours(user);
        model.addAttribute("tours", tours);

        return "reservedTours";
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
