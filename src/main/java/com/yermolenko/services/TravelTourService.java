package com.yermolenko.services;

import com.yermolenko.controllers.forms.SearchTourParams;
import com.yermolenko.dto.TravelTourResortDto;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

/**
 * Interface TravelTourService.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface TravelTourService {

    /**
     * Method getTours.
     *
     * @param searchTourParams of type SearchTourParams
     * @return List<TravelTour>
     */
    List<TravelTour> getTravelToursForManager(SearchTourParams searchTourParams);

    List<TravelTour> getTravelToursForUser(SearchTourParams searchTourParams);

    /**
     * Method getTour.
     *
     * @param id of type int
     * @return TravelTour
     */
    TravelTour getTravelTour(int id);

    /**
     * Method updateTour.
     *
     * @param id of type int
     * @param tour of type TravelTour
     * @return boolean
     */
    boolean updateTravelTour(int id, TravelTour tour);

    /**
     * Method deleteTour.
     *
     * @param id of type int
     * @return boolean
     */
    boolean deleteTravelTour(int id);

    /**
     * Method addTour.
     *
     * @param tour of type TravelTour
     * @return boolean
     */
    boolean addTravelTour(TravelTour tour);

    /**
     * Method reservationTour.
     *
     * @param user of type User
     * @param travelTour of type TravelTour
     * @return boolean
     */
    boolean reservationTravelTour(User user, TravelTour travelTour);

    /**
     * Method getReservedTours.
     *
     * @param user of type User
     * @return List<TravelTour>
     */
    List<TravelTour> getReservedTravelTours(User user);

    /**
     * Method getListOfReservedTourUsers.
     *
     * @param tour of type TravelTour
     * @return List<User>
     */
    List<User> getListOfReservedTravelTourUsers(TravelTour tour);

    List<TravelTourResortDto> getTravelTourResortStat();

}
