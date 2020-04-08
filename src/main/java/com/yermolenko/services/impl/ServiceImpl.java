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
    public List<TravelTour> getSomeTours(SearchTourParams searchTourParams) {
        return userDAO.getSomeTours(searchTourParams);
    }
}
