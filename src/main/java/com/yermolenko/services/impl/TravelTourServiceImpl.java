package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.controllers.forms.SearchTourParams;
import com.yermolenko.dto.BranchManagerDto;
import com.yermolenko.dto.TravelTourResortDto;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelTourService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Class TravelTourServiceImpl.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Service
public class TravelTourServiceImpl implements TravelTourService {

    private final TravelTourDAO travelTourDAO;

    /**
     * Constructor TravelTourServiceImpl creates a new TravelTourServiceImpl instance.
     *
     * @param travelTourDAO of type TravelTourDAO
     */
    public TravelTourServiceImpl(TravelTourDAO travelTourDAO) {
        this.travelTourDAO = travelTourDAO;
    }

    /**
     * Method getTours.
     *
     * @param searchTourParams of type SearchTourParams
     * @return List<TravelTour>
     */
    @Override
    public List<TravelTour> getTravelToursForManager(SearchTourParams searchTourParams) {
        if ("".equals(searchTourParams.getDestination())
                && searchTourParams.getBeginDate() == null
                && searchTourParams.getEndDate() == null
                && searchTourParams.getMinCost() == null
                && searchTourParams.getMaxCost() == null) {
            return travelTourDAO.getAllTravelToursForManager(searchTourParams);
        }
        return travelTourDAO.getSomeTravelToursForManager(searchTourParams);
    }

    @Override
    public List<TravelTour> getTravelToursForUser(SearchTourParams searchTourParams) {
        if ("".equals(searchTourParams.getDestination())
                && searchTourParams.getBeginDate() == null
                && searchTourParams.getEndDate() == null
                && searchTourParams.getMinCost() == null
                && searchTourParams.getMaxCost() == null) {
            return travelTourDAO.getAllTravelToursForUser(searchTourParams);
        }
        return travelTourDAO.getSomeTravelToursForUser(searchTourParams);
    }

    /**
     * Method getTour.
     *
     * @param id of type int
     * @return TravelTour
     */
    @Override
    public TravelTour getTravelTour(int id) {
        return travelTourDAO.getTravelTourById(id);
    }

    /**
     * Method updateTour.
     *
     * @param id of type int
     * @param tour of type TravelTour
     * @return boolean
     */
    @Override
    public boolean updateTravelTour(int id, TravelTour tour) {
        return travelTourDAO.updateTravelTour(id, tour);
    }

    /**
     * Method deleteTour.
     *
     * @param id of type int
     * @return boolean
     */
    @Override
    public boolean deleteTravelTour(int id) {
        return travelTourDAO.deleteTravelTour(id);
    }

    /**
     * Method addTour.
     *
     * @param tour of type TravelTour
     * @return boolean
     */
    @Override
    public boolean addTravelTour(TravelTour tour) {
        return travelTourDAO.addTravelTour(tour);
    }

    /**
     * Method reservationTour.
     *
     * @param user of type User
     * @param travelTour of type TravelTour
     * @return boolean
     */
    @Override
    @Transactional
    public boolean reservationTravelTour(User user, TravelTour travelTour) {
        return travelTourDAO.reservationTravelTour(user, travelTour);
    }

    /**
     * Method getReservedTours.
     *
     * @param user of type User
     * @return List<TravelTour>
     */
    @Override
    public List<TravelTour> getReservedTravelTours(User user) {
        return travelTourDAO.getReservedTravelTours(user);
    }

    /**
     * Method getListOfReservedTourUsers.
     *
     * @param tour of type TravelTour
     * @return List<User>
     */
    @Override
    public List<User> getListOfReservedTravelTourUsers(TravelTour tour) {
        return travelTourDAO.getListOfReservedTravelTourUsers(tour);
    }

    @Override
    public List<TravelTourResortDto> getTravelTourResortStat() {
        return travelTourDAO.getTravelTourResortStat();
    }

}
