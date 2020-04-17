package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.TravelTourService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TravelTourServiceImpl implements TravelTourService {

    private final TravelTourDAO travelTourDAO;

    public TravelTourServiceImpl(TravelTourDAO travelTourDAO) {
        this.travelTourDAO = travelTourDAO;
    }

    @Override
    public List<TravelTour> getTours(SearchTourParams searchTourParams) {
        if ("".equals(searchTourParams.getDestination())
                && searchTourParams.getBeginDate() == null
                && searchTourParams.getEndDate() == null
                && searchTourParams.getMinCost() == null
                && searchTourParams.getMaxCost() == null) {
            return travelTourDAO.getAllTours(searchTourParams);
        }
        return travelTourDAO.getSomeTours(searchTourParams);
    }

    @Override
    public TravelTour getTour(int id) {
        return travelTourDAO.getTour(id);
    }

    @Override
    public void updateTour(int id, TravelTour tour) {
        travelTourDAO.updateTour(id, tour);
    }

    @Override
    public void deleteTour(int id) {
        travelTourDAO.deleteTour(id);
    }

    @Override
    public void addTour(TravelTour tour) {
        travelTourDAO.addTour(tour);
    }

}