package com.yermolenko.services;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;

import java.util.List;

public interface TravelTourService {

    List<TravelTour> getTours(SearchTourParams searchTourParams);

    TravelTour getTour(int id);

    void updateTour(int id, TravelTour tour);

    void deleteTour(int id);

    void addTour(TravelTour tour);

    boolean reservationTour(User user, TravelTour travelTour);

}
