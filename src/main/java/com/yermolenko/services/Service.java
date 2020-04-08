package com.yermolenko.services;

import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;

import java.util.List;

public interface Service {

    List<TravelTour> getSomeTours(SearchTourParams searchTourParams);

}
