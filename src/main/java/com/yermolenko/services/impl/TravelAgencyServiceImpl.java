package com.yermolenko.services.impl;

import com.yermolenko.dao.AgencyBranchDAO;
import com.yermolenko.dao.ResortDAO;
import com.yermolenko.dao.TravelCarrierDAO;
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

    private final AgencyBranchDAO agencyBranchDAO;
    private final ResortDAO resortDAO;
    private final TravelCarrierDAO travelCarrierDAO;

    /**
     * Constructor TravelAgencyServiceImpl creates a new TravelAgencyServiceImpl instance.
     *  @param agencyBranchDAO
     * @param resortDAO
     * @param travelCarrierDAO
     */
    public TravelAgencyServiceImpl(AgencyBranchDAO agencyBranchDAO, ResortDAO resortDAO, TravelCarrierDAO travelCarrierDAO) {
        this.agencyBranchDAO = agencyBranchDAO;
        this.resortDAO = resortDAO;
        this.travelCarrierDAO = travelCarrierDAO;
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
        return agencyBranchDAO.getAgencyBranches();
    }

    /**
     * Method getAgencyBranchById.
     *
     * @param id of type int
     * @return AgencyBranch
     */
    @Override
    public AgencyBranch getAgencyBranchById(int id) {
        return agencyBranchDAO.getAgencyBranchById(id);
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
        return resortDAO.getResorts();
    }

    /**
     * Method getResortById.
     *
     * @param id of type int
     * @return Resort
     */
    @Override
    public Resort getResortById(int id) {
        return resortDAO.getResortById(id);
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
        return travelCarrierDAO.getTravelCarriers();
    }

    /**
     * Method getTravelCarrierById.
     *
     * @param id of type int
     * @return TravelCarrier
     */
    @Override
    public TravelCarrier getTravelCarrierById(int id) {
        return travelCarrierDAO.getTravelCarrierById(id);
    }
}
