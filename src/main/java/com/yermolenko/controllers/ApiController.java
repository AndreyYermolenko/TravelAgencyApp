package com.yermolenko.controllers;

import com.yermolenko.model.Role;
import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelTourService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class ApiController {

    private final TravelTourService travelTourService;

    public ApiController(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
    }

    @RequestMapping("/getToursQuick")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> getToursQuick(@RequestParam(value = "") String destination) {
        SearchTourParams params = new SearchTourParams();
        params.setDestination(destination);
        List<TravelTour> tourList = travelTourService.getTours(params);

        return new ResponseEntity<>(tourList, HttpStatus.OK);
    }

    @RequestMapping("/getCurrentUserRoles")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> getCurrentUserRoles() {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();
        List<String> list = new ArrayList<>();
        for (GrantedAuthority role: authorities) {
            list.add(role.getAuthority());
        }
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/api/reservationTour")
    @PreAuthorize("hasAuthority('user')")
    public ResponseEntity<?> reservationTour(@RequestParam(name = "id") int idTour) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        TravelTour tour = travelTourService.getTour(idTour);
        boolean result = travelTourService.reservationTour(user, tour);

        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
    }
}
