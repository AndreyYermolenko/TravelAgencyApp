package com.yermolenko.controllers;

import com.yermolenko.forms.LoginForm;
import com.yermolenko.services.RestService;
import com.yermolenko.transfer.TokenDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

//    private final TravelTourService travelTourService;

    private final RestService restService;

    public ApiController(RestService restService) {
        this.restService = restService;
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

    @PostMapping("/api/sign_in")
    public ResponseEntity<TokenDto> login(@RequestBody LoginForm loginForm) {
        return ResponseEntity.ok(restService.login(loginForm));
    }
}
