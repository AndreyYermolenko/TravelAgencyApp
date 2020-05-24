package com.yermolenko.controllers;

import com.yermolenko.controllers.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.OtherServices;
import com.yermolenko.services.TravelTourService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Class MainControllerForUser.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
@Controller
public class MainControllerForUser {

    private final TravelTourService travelTourService;

    private final OtherServices otherServices;

    /**
     * Constructor MainControllerForUser creates a new MainControllerForUser instance.
     *
     * @param travelTourService of type TravelTourService
     * @param otherServices of type OtherServices
     */
    public MainControllerForUser(TravelTourService travelTourService, OtherServices otherServices) {
        this.travelTourService = travelTourService;
        this.otherServices = otherServices;
    }

    /**
     * Method getTours.
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
        List<TravelTour> tours = travelTourService.getTravelToursForManager(tourParams);
        model.addAttribute("tours", tours);

        return "advancedSearchTours";
    }

    /**
     * Controller getToursQuick is responsible for quick search tours.
     *
     * @return String
     */
    @RequestMapping("/tours/quickSearch")
    @PreAuthorize("hasAuthority('user')")
    public String getToursQuick() {
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
        List<TravelTour> tours = travelTourService.getTravelToursForManager(params);
        model.addAttribute("tours", tours);

        return "showTours";
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
        TravelTour tour = travelTourService.getTravelTour(idTour);
        boolean result = travelTourService.reservationTravelTour(user, tour);
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

        List<TravelTour> tours = travelTourService.getReservedTravelTours(user);
        model.addAttribute("tours", tours);

        return "reservedTours";
    }

}
