package com.yermolenko.services.impl;

import com.yermolenko.dao.TravelAgencyDAO;
import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;
import com.yermolenko.services.TravelAgencyService;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j
@Service
public class TravelAgencyServiceImpl implements TravelAgencyService {

    private final TravelAgencyDAO travelAgencyDAO;

    public TravelAgencyServiceImpl(TravelAgencyDAO travelAgencyDAO) {
        this.travelAgencyDAO = travelAgencyDAO;
    }

    @Override
    public List<AgencyBranch> getAgencyBranches() {
        return travelAgencyDAO.getAgencyBranches();
    }

    @Override
    public AgencyBranch getAgencyBranchById(int id) {
        return travelAgencyDAO.getAgencyBranchById(id);
    }

    @Override
    public List<Resort> getResorts() {
        return travelAgencyDAO.getResorts();
    }

    @Override
    public Resort getResortById(int id) {
        return travelAgencyDAO.getResortById(id);
    }

    @Override
    public List<TravelCarrier> getTravelCarriers() {
        return travelAgencyDAO.getTravelCarriers();
    }

    @Override
    public TravelCarrier getTravelCarrierById(int id) {
        return travelAgencyDAO.getTravelCarrierById(id);
    }
}
