package com.yermolenko.services;

import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;

import java.util.List;

public interface TravelAgencyService {

    List<AgencyBranch> getAgencyBranches();

    AgencyBranch getAgencyBranchById(int id);

    List<Resort> getResorts();

    Resort getResortById(int id);

    List<TravelCarrier> getTravelCarriers();

    TravelCarrier getTravelCarrierById(int id);

}
