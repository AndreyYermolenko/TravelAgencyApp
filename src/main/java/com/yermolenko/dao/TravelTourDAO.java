package com.yermolenko.dao;

import com.yermolenko.controllers.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

/**
 * Interface TravelTourDAO is designed to receive and modify travel tour data.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface TravelTourDAO {

    /**
     * Method getAllTours gets the all tours data from database.
     *
     * @param searchTourParams of type SearchTourParams
     * @return List<TravelTour>
     */
    List<TravelTour> getAllTravelToursForManager(SearchTourParams searchTourParams);

    List<TravelTour> getAllTravelToursForUser(SearchTourParams searchTourParams);

    /**
     * Method getSomeTours gets some tours data from database.
     *
     * @param searchTourParams of type SearchTourParams
     * @return List<TravelTour>
     */
    List<TravelTour> getSomeTravelToursForManager(SearchTourParams searchTourParams);

    List<TravelTour> getSomeTravelToursForUser(SearchTourParams searchTourParams);

    /**
     * Method getTourById gets tour data by id from database.
     *
     * @param id of type int
     * @return TravelTour
     */
    TravelTour getTravelTourById(int id);

    /**
     * Method reservationTour adds tour reservation data to the database.
     *
     * @param user of type User
     * @param travelTour of type TravelTour
     * @return boolean
     */
    boolean reservationTravelTour(User user, TravelTour travelTour);

    /**
     * Method getReservedTours gets reserved tours data from database.
     *
     * @param user of type User
     * @return List<TravelTour>
     */
    List<TravelTour> getReservedTravelTours(User user);

    /**
     * Method updateTour updates tour data to the database.
     *
     * @param id of type int
     * @param travelTour of type TravelTour
     * @return boolean
     */
    boolean updateTravelTour(int id, TravelTour travelTour);

    /**
     * Method deleteTour deletes tour data from the database.
     *
     * @param id of type int
     * @return boolean
     */
    boolean deleteTravelTour(int id);

    /**
     * Method addTour adds tour data to the database.
     *
     * @param travelTour of type TravelTour
     * @return boolean
     */
    boolean addTravelTour(TravelTour travelTour);

    /**
     * Method getListOfReservedTourUsers gets list of reserved tour users data from database.
     *
     * @param tour of type TravelTour
     * @return List<User>
     */
    List<User> getListOfReservedTravelTourUsers(TravelTour tour);

}
