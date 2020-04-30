package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
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
        return travelTourDAO.getTourById(id);
    }

    @Override
    public boolean updateTour(int id, TravelTour tour) {
        return travelTourDAO.updateTour(id, tour);
    }

    @Override
    public boolean deleteTour(int id) {
        return travelTourDAO.deleteTour(id);
    }

    @Override
    public boolean addTour(TravelTour tour) {
        return travelTourDAO.addTour(tour);
    }

    @Override
    public boolean reservationTour(User user, TravelTour travelTour) {
        return travelTourDAO.reservationTour(user, travelTour);
    }

    @Override
    public List<TravelTour> getReservedTours(User user) {
        return travelTourDAO.getReservedTours(user);
    }

    @Override
    public List<User> getListOfReservedTourUsers(TravelTour tour) {
        return travelTourDAO.getListOfReservedTourUsers(tour);
    }

}
