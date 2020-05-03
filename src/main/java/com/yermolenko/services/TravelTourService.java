package com.yermolenko.services;

import com.yermolenko.forms.SearchTourParams;
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
    List<TravelTour> getTours(SearchTourParams searchTourParams);

    /**
     * Method getTour.
     *
     * @param id of type int
     * @return TravelTour
     */
    TravelTour getTour(int id);

    /**
     * Method updateTour.
     *
     * @param id of type int
     * @param tour of type TravelTour
     * @return boolean
     */
    boolean updateTour(int id, TravelTour tour);

    /**
     * Method deleteTour.
     *
     * @param id of type int
     * @return boolean
     */
    boolean deleteTour(int id);

    /**
     * Method addTour.
     *
     * @param tour of type TravelTour
     * @return boolean
     */
    boolean addTour(TravelTour tour);

    /**
     * Method reservationTour.
     *
     * @param user of type User
     * @param travelTour of type TravelTour
     * @return boolean
     */
    boolean reservationTour(User user, TravelTour travelTour);

    /**
     * Method getReservedTours.
     *
     * @param user of type User
     * @return List<TravelTour>
     */
    List<TravelTour> getReservedTours(User user);

    /**
     * Method getListOfReservedTourUsers.
     *
     * @param tour of type TravelTour
     * @return List<User>
     */
    List<User> getListOfReservedTourUsers(TravelTour tour);

}
