package com.yermolenko.dao;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;

import java.util.List;

public interface UserDAO {

    List<TravelTour> getAllTours(SearchTourParams searchTourParams);

    List<TravelTour> getSomeTours(SearchTourParams searchTourParams);

    TravelTour getTour(int id);

    boolean reservationTour(TravelTour travelTour);

    void updateTour(int id, TravelTour travelTour);

    void deleteTour(int id);

    void addTour(TravelTour travelTour);

}
