package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelAgencyDAO;
import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;
import com.yermolenko.services.TravelAgencyService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Class TravelAgencyServiceImpl.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
@Log4j
@Service
public class TravelAgencyServiceImpl implements TravelAgencyService {

    private final TravelAgencyDAO travelAgencyDAO;

    /**
     * Constructor TravelAgencyServiceImpl creates a new TravelAgencyServiceImpl instance.
     *
     * @param travelAgencyDAO of type TravelAgencyDAO
     */
    public TravelAgencyServiceImpl(TravelAgencyDAO travelAgencyDAO) {
        this.travelAgencyDAO = travelAgencyDAO;
    }

    /**
     * Method getAgencyBranches returns the agencyBranches of this TravelAgencyServiceImpl object.
     *
     *
     *
     * @return the agencyBranches (type List<AgencyBranch>) of this TravelAgencyServiceImpl object.
     */
    @Override
    public List<AgencyBranch> getAgencyBranches() {
        return travelAgencyDAO.getAgencyBranches();
    }

    /**
     * Method getAgencyBranchById.
     *
     * @param id of type int
     * @return AgencyBranch
     */
    @Override
    public AgencyBranch getAgencyBranchById(int id) {
        return travelAgencyDAO.getAgencyBranchById(id);
    }

    /**
     * Method getResorts returns the resorts of this TravelAgencyServiceImpl object.
     *
     *
     *
     * @return the resorts (type List<Resort>) of this TravelAgencyServiceImpl object.
     */
    @Override
    public List<Resort> getResorts() {
        return travelAgencyDAO.getResorts();
    }

    /**
     * Method getResortById.
     *
     * @param id of type int
     * @return Resort
     */
    @Override
    public Resort getResortById(int id) {
        return travelAgencyDAO.getResortById(id);
    }

    /**
     * Method getTravelCarriers returns the travelCarriers of this TravelAgencyServiceImpl object.
     *
     *
     *
     * @return the travelCarriers (type List<TravelCarrier>) of this TravelAgencyServiceImpl object.
     */
    @Override
    public List<TravelCarrier> getTravelCarriers() {
        return travelAgencyDAO.getTravelCarriers();
    }

    /**
     * Method getTravelCarrierById.
     *
     * @param id of type int
     * @return TravelCarrier
     */
    @Override
    public TravelCarrier getTravelCarrierById(int id) {
        return travelAgencyDAO.getTravelCarrierById(id);
    }
}
