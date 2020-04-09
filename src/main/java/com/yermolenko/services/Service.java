package com.yermolenko.services;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;

import java.util.List;

public interface Service {

    List<TravelTour> getTours(SearchTourParams searchTourParams);

    TravelTour getTour(int id);

    void updateTour(int id, TravelTour tour);

    void deleteTour(int id);

}
