package com.yermolenko.controllers;

import com.yermolenko.dto.TokenDto;
import com.yermolenko.dto.TravelTourDtoForUser;
import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.forms.TravelTourForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.RestService;
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

import static com.yermolenko.utils.Converters.from;

@RestController
public class ApiController {

    private final TravelTourService travelTourService;

    private final RestService restService;

    private final UserService userService;

    public ApiController(TravelTourService travelTourService, RestService restService, UserService userService) {
        this.travelTourService = travelTourService;
        this.restService = restService;
        this.userService = userService;
    }

    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(restService.login(loginForm));
    }

    @PostMapping("/api/sign_up")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        User user = from(userForm);
        boolean result = userService.registrationUser(user);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/api/tours")
    public ResponseEntity<?> getTours(@RequestBody SearchTourParams tourParams) {
        System.out.println(tourParams.toString());
        List<TravelTour> tours = travelTourService.getTours(tourParams);

        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities();

        if (authorities.contains(new SimpleGrantedAuthority("manager"))) {
            return ResponseEntity.ok(tours);
        } else {
            List<TravelTourDtoForUser> tourDtoList = from(tours);
            return ResponseEntity.ok(tourDtoList);
        }
    }

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

    @PostMapping("/api/updateTour")
    public ResponseEntity<?> updateTour(@RequestBody TravelTourForm tourUpdate,
                             @RequestParam int id) {
        boolean result = travelTourService.updateTour(id, from(tourUpdate));

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/api/deleteTour")
    public ResponseEntity<?> deleteTour(@RequestParam int id) {
        boolean result = travelTourService.deleteTour(id);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/api/addTour")
    public ResponseEntity<?> addTour(@RequestBody TravelTourForm tourAdd) {
        boolean result = travelTourService.addTour(from(tourAdd));

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }


    @GetMapping("/api/reservedTours")
    public ResponseEntity<List<TravelTour>> getReservedTours() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
        String currentUserEmail = userDetails.getUsername();
        User user = userService.findUserByEmail(currentUserEmail);

        List<TravelTour> tours = travelTourService.getReservedTours(user);

        return ResponseEntity.ok(tours);
    }

    @GetMapping("/api/listOfReservedTourUsers")
    public ResponseEntity<List<User>> listOfReservedTourUsers(@RequestParam int id) {
        TravelTour tour = new TravelTour();
        tour.setId(id);

        List<User> users = travelTourService.getListOfReservedTourUsers(tour);

        return ResponseEntity.ok(users);
    }
}
