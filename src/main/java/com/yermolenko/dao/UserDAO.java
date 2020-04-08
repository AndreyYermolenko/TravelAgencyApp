package com.yermolenko.dao;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;

import java.util.List;

public interface UserDAO {

    List<TravelTour> getAllTours();

    List<TravelTour> getSomeTours(SearchTourParams searchTourParams);

    boolean buyTour(TravelTour travelTour);

    boolean updateTour(TravelTour travelTour);

    boolean deleteTour(TravelTour travelTour);

    boolean addTour(TravelTour travelTour);

}
