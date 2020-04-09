package com.yermolenko.services.impl;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.services.Service;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceImpl implements Service {

    private final UserDAO userDAO;

    public ServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<TravelTour> getTours(SearchTourParams searchTourParams) {
        if ("".equals(searchTourParams.getDestination())
                && searchTourParams.getBeginDate() == null
                && searchTourParams.getEndDate() == null
                && searchTourParams.getMinCost() == null
                && searchTourParams.getMaxCost() == null) {
            return userDAO.getAllTours();
        }
        return userDAO.getSomeTours(searchTourParams);
    }

    @Override
    public TravelTour getTour(int id) {
        return userDAO.getTour(id);
    }

    @Override
    public void updateTour(int id, TravelTour tour) {
        userDAO.updateTour(id, tour);
    }

    @Override
    public void deleteTour(int id) {
        userDAO.deleteTour(id);
    }

}
