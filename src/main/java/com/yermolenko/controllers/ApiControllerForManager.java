package com.yermolenko.controllers;

import com.yermolenko.controllers.forms.TravelTourForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelAgencyService;
import com.yermolenko.services.TravelTourService;
import com.yermolenko.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yermolenko.utils.Converters.from;

@RestController
public class ApiControllerForManager {

    private final TravelTourService travelTourService;

    private final UserService userService;

    private final TravelAgencyService travelAgencyService;

    public ApiControllerForManager(TravelTourService travelTourService, UserService userService, TravelAgencyService travelAgencyService) {
        this.travelTourService = travelTourService;
        this.userService = userService;
        this.travelAgencyService = travelAgencyService;
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
