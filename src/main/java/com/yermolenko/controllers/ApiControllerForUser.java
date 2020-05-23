package com.yermolenko.controllers;

import com.yermolenko.controllers.forms.SearchTourParams;
import com.yermolenko.model.*;
import com.yermolenko.services.TravelAgencyService;
import com.yermolenko.services.TravelTourService;
import com.yermolenko.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

/**
 * Class ApiController contains api controllers.
 *
 * @author Andrey
 * Created on 02.05.2020
 */
@RestController
public class ApiControllerForUser {

    private final TravelTourService travelTourService;

    private final UserService userService;

    private final TravelAgencyService travelAgencyService;

    /**
     * Constructor ApiController creates a new ApiController instance.
     *  @param travelTourService of type TravelTourService
     * @param userService of type UserService
     * @param travelAgencyService
     */
    public ApiControllerForUser(TravelTourService travelTourService, UserService userService, TravelAgencyService travelAgencyService) {
        this.travelTourService = travelTourService;
        this.userService = userService;
        this.travelAgencyService = travelAgencyService;
    }

    /**
     * Controller getTours is responsible for getting travel tours.
     *
     * @param tourParams of type SearchTourParams
     * @return ResponseEntity<?>
     */
    @PostMapping("/api/tours")
    public ResponseEntity<?> getTours(@RequestBody SearchTourParams tourParams) {

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("manager"))) {
            return ResponseEntity.ok(travelTourService.getToursForManager(tourParams));
        } else {
            return ResponseEntity.ok(travelTourService.getToursForUser(tourParams));
        }
    }

    /**
     * Controller reservationTour is responsible for reservation tour for user.
     *
     * @param id of type int
     * @return ResponseEntity<?>
     */
    @GetMapping("/api/reservationTour")
    public ResponseEntity<?> reservationTour(@RequestParam int id) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String currentUserEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(currentUserEmail);
        TravelTour tour = travelTourService.getTour(id);
        boolean result = travelTourService.reservationTour(user, tour);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Controller getReservedTours returns the reservedTours of this ApiController object.
     *
     *
     *
     * @return the reservedTours (type ResponseEntity<List<TravelTour>>) of this ApiController object.
     */
    @GetMapping("/api/reservedTours")
    public ResponseEntity<List<TravelTour>> getReservedTours() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String currentUserEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(currentUserEmail);
        List<TravelTour> tours = travelTourService.getReservedTours(user);

        return ResponseEntity.ok(tours);
    }

    /**
     * Method getAgencyBranches returns the agencyBranches of this ApiController object.
     *
     *
     *
     * @return the agencyBranches (type ResponseEntity<List<AgencyBranch>>) of this ApiController object.
     */
    @GetMapping("/api/agencyBranch")
    public ResponseEntity<List<AgencyBranch>> getAgencyBranches() {
        List<AgencyBranch> agencyBranchList= travelAgencyService.getAgencyBranches();

        return ResponseEntity.ok(agencyBranchList);
    }

    /**
     * Method getResorts returns the resorts of this ApiController object.
     *
     *
     *
     * @return the resorts (type ResponseEntity<List<Resort>>) of this ApiController object.
     */
    @GetMapping("/api/resort")
    public ResponseEntity<List<Resort>> getResorts() {
        List<Resort> resortList= travelAgencyService.getResorts();

        return ResponseEntity.ok(resortList);
    }

    /**
     * Method getTravelCarriers returns the travelCarriers of this ApiController object.
     *
     *
     *
     * @return the travelCarriers (type ResponseEntity<List<TravelCarrier>>) of this ApiController object.
     */
    @GetMapping("/api/carrier")
    public ResponseEntity<List<TravelCarrier>> getTravelCarriers() {
        List<TravelCarrier> carrierList= travelAgencyService.getTravelCarriers();

        return ResponseEntity.ok(carrierList);
    }
}
