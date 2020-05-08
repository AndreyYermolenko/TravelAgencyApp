package com.yermolenko.controllers;

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

/**
 * Class ApiController contains api controllers.
 *
 * @author Andrey
 * Created on 02.05.2020
 */
@RestController
public class ApiController {

    private final TravelTourService travelTourService;

    private final RestService restService;

    private final UserService userService;

    /**
     * Constructor ApiController creates a new ApiController instance.
     *
     * @param travelTourService of type TravelTourService
     * @param restService of type RestService
     * @param userService of type UserService
     */
    public ApiController(TravelTourService travelTourService, RestService restService, UserService userService) {
        this.travelTourService = travelTourService;
        this.restService = restService;
        this.userService = userService;
    }

    /**
     * Controller login forms a request for api to receive a token.
     *
     * @param loginForm of type LoginForm
     * @return ResponseEntity<TokenDto>
     */
    @PostMapping("/api/login")
    public ResponseEntity<String> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(restService.login(loginForm).getValue());
    }

    /**
     * Controller addUser is responsible for adding a new user.
     *
     * @param userForm of type UserForm
     * @return ResponseEntity<Object>
     */
    @PostMapping("/api/sign_up")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        User user = from(userForm);
        boolean result = userService.registrationUser(user);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
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
     * Controller updateTour is responsible for updating tour for manager.
     *
     * @param tourUpdate of type TravelTourForm
     * @param id of type int
     * @return ResponseEntity<?>
     */
    @PostMapping("/api/updateTour")
    public ResponseEntity<?> updateTour(@RequestBody TravelTourForm tourUpdate,
                             @RequestParam int id) {
        boolean result = travelTourService.updateTour(id, from(tourUpdate));

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Controller deleteTour is responsible for deleting tour for manager.
     *
     * @param id of type int
     * @return ResponseEntity<?>
     */
    @GetMapping("/api/deleteTour")
    public ResponseEntity<?> deleteTour(@RequestParam int id) {
        boolean result = travelTourService.deleteTour(id);

        if (result) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    /**
     * Controller addTour is responsible for adding a new tour.
     *
     * @param tourAdd of type TravelTourForm
     * @return ResponseEntity<?>
     */
    @PostMapping("/api/addTour")
    public ResponseEntity<?> addTour(@RequestBody TravelTourForm tourAdd) {
        boolean result = travelTourService.addTour(from(tourAdd));

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
     * Controller listOfReservedTourUsers is responsible for returns list of reserved tour users for manager.
     *
     * @param id of type int
     * @return ResponseEntity<List < User>>
     */
    @GetMapping("/api/listOfReservedTourUsers")
    public ResponseEntity<List<User>> listOfReservedTourUsers(@RequestParam int id) {
        TravelTour tour = new TravelTour();
        tour.setId(id);

        List<User> users = travelTourService.getListOfReservedTourUsers(tour);

        return ResponseEntity.ok(users);
    }
}
