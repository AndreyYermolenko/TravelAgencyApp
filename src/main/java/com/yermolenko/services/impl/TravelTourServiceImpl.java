package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import com.yermolenko.services.TravelTourService;
import org.springframework.stereotype.Service;

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

    /**
     * Method getTour.
     *
     * @param id of type int
     * @return TravelTour
     */
    @Override
    public TravelTour getTour(int id) {
        return travelTourDAO.getTourById(id);
    }

    /**
     * Method updateTour.
     *
     * @param id of type int
     * @param tour of type TravelTour
     * @return boolean
     */
    @Override
    public boolean updateTour(int id, TravelTour tour) {
        return travelTourDAO.updateTour(id, tour);
    }

    /**
     * Method deleteTour.
     *
     * @param id of type int
     * @return boolean
     */
    @Override
    public boolean deleteTour(int id) {
        return travelTourDAO.deleteTour(id);
    }

    /**
     * Method addTour.
     *
     * @param tour of type TravelTour
     * @return boolean
     */
    @Override
    public boolean addTour(TravelTour tour) {
        return travelTourDAO.addTour(tour);
    }

    /**
     * Method reservationTour.
     *
     * @param user of type User
     * @param travelTour of type TravelTour
     * @return boolean
     */
    @Override
    public boolean reservationTour(User user, TravelTour travelTour) {
        return travelTourDAO.reservationTour(user, travelTour);
    }

    /**
     * Method getReservedTours.
     *
     * @param user of type User
     * @return List<TravelTour>
     */
    @Override
    public List<TravelTour> getReservedTours(User user) {
        return travelTourDAO.getReservedTours(user);
    }

    /**
     * Method getListOfReservedTourUsers.
     *
     * @param tour of type TravelTour
     * @return List<User>
     */
    @Override
    public List<User> getListOfReservedTourUsers(TravelTour tour) {
        return travelTourDAO.getListOfReservedTourUsers(tour);
    }

}
