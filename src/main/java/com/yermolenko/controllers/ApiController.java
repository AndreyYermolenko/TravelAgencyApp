package com.yermolenko.controllers;

import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.RestService;
import com.yermolenko.services.TravelTourService;
import com.yermolenko.services.UserService;
import com.yermolenko.dto.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yermolenko.utils.Converters.convertUserFormToUser;

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

//    public ApiController(TravelTourService travelTourService) {
//        this.travelTourService = travelTourService;
//    }

//    @RequestMapping("/getToursQuick")
//    @PreAuthorize("hasAuthority('user')")
//    public ResponseEntity<?> getToursQuick(@RequestParam(value = "") String destination) {
//        SearchTourParams params = new SearchTourParams();
//        params.setDestination(destination);
//        List<TravelTour> tourList = travelTourService.getTours(params);
//
//        return new ResponseEntity<>(tourList, HttpStatus.OK);
//    }
//
//    @RequestMapping("/getCurrentUserRoles")
//    @PreAuthorize("hasAuthority('user')")
//    public ResponseEntity<?> getCurrentUserRoles() {
//        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication()
//                .getAuthorities();
//        List<String> list = new ArrayList<>();
//        for (GrantedAuthority role: authorities) {
//            list.add(role.getAuthority());
//        }
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @GetMapping("/reservationTour")
//    @PreAuthorize("hasAuthority('user')")
//    public ResponseEntity<?> reservationTour(@RequestParam(name = "id") int idTour) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        TravelTour tour = travelTourService.getTour(idTour);
//        boolean result = travelTourService.reservationTour(user, tour);
//
//        return new ResponseEntity<Boolean>(result, HttpStatus.OK);
//    }

    @PostMapping("/api/login")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(restService.login(loginForm));
    }

    @PostMapping("/api/sign_up")
    public ResponseEntity<Object> addUser(@RequestBody UserForm userForm) {
        User user = convertUserFormToUser(userForm);
        userService.registrationUser(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/tours")
    public ResponseEntity<List<TravelTour>> getTours(@RequestBody SearchTourParams tourParams) {
        System.out.println(tourParams.toString());

        List<TravelTour> tours = travelTourService.getTours(tourParams);

        return ResponseEntity.ok(tours);
    }
}
