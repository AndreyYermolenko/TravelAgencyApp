package com.yermolenko.services;

import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

public interface TravelTourService {

    List<TravelTour> getTours(SearchTourParams searchTourParams);

    TravelTour getTour(int id);

    boolean updateTour(int id, TravelTour tour);

    boolean deleteTour(int id);

    boolean addTour(TravelTour tour);

    boolean reservationTour(User user, TravelTour travelTour);

    List<TravelTour> getReservedTours(User user);

    List<User> getListOfReservedTourUsers(TravelTour tour);

}
