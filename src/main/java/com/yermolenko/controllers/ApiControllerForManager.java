package com.yermolenko.controllers;

import com.yermolenko.controllers.forms.TravelTourForm;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelTourService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.yermolenko.utils.Converters.from;

@RestController
public class ApiControllerForManager {

    private final TravelTourService travelTourService;

    public ApiControllerForManager(TravelTourService travelTourService) {
        this.travelTourService = travelTourService;
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
        boolean result = travelTourService.updateTravelTour(id, from(tourUpdate));

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
        boolean result = travelTourService.deleteTravelTour(id);

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
        boolean result = travelTourService.addTravelTour(from(tourAdd));

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
        List<User> users = travelTourService.getListOfReservedTravelTourUsers(tour);

        return ResponseEntity.ok(users);
    }

}
