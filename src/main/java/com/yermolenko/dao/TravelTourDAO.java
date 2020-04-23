package com.yermolenko.dao;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

public interface TravelTourDAO {

    List<TravelTour> getAllTours(SearchTourParams searchTourParams);

    List<TravelTour> getSomeTours(SearchTourParams searchTourParams);

    TravelTour getTourById(int id);

    boolean reservationTour(User user, TravelTour travelTour);

    List<TravelTour> getReservedTours(User user);

    void updateTour(int id, TravelTour travelTour);

    void deleteTour(int id);

    void addTour(TravelTour travelTour);

    List<User> getListOfReservedTourUsers(TravelTour tour);

}
