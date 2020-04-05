package com.yermolenko.dao;

import com.yermolenko.model.TravelTour;

import java.util.ArrayList;

public interface UserDAO {

    ArrayList<TravelTour> getAllTours();

    ArrayList<TravelTour> getSomeTours();

    boolean buyTour(TravelTour travelTour);

    boolean updateTour(TravelTour travelTour);

    boolean deleteTour(TravelTour travelTour);

    boolean addTour(TravelTour travelTour);

}
