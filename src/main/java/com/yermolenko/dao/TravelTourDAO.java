package com.yermolenko.dao;

import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

public interface TravelTourDAO {

    List<TravelTour> getAllTours(SearchTourParams searchTourParams);

    List<TravelTour> getSomeTours(SearchTourParams searchTourParams);

    TravelTour getTourById(int id);

    boolean reservationTour(User user, TravelTour travelTour);

    List<TravelTour> getReservedTours(User user);

    boolean updateTour(int id, TravelTour travelTour);

    boolean deleteTour(int id);

    boolean addTour(TravelTour travelTour);

    List<User> getListOfReservedTourUsers(TravelTour tour);

}
