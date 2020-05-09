package com.yermolenko.services;

import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;

import java.util.List;

/**
 * Interface TravelAgencyService.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
public interface TravelAgencyService {

    /**
     * Method getAgencyBranches returns the agencyBranches of this TravelAgencyService object.
     *
     *
     *
     * @return the agencyBranches (type List<AgencyBranch>) of this TravelAgencyService object.
     */
    List<AgencyBranch> getAgencyBranches();

    /**
     * Method getAgencyBranchById.
     *
     * @param id of type int
     * @return AgencyBranch
     */
    AgencyBranch getAgencyBranchById(int id);

    /**
     * Method getResorts returns the resorts of this TravelAgencyService object.
     *
     *
     *
     * @return the resorts (type List<Resort>) of this TravelAgencyService object.
     */
    List<Resort> getResorts();

    /**
     * Method getResortById.
     *
     * @param id of type int
     * @return Resort
     */
    Resort getResortById(int id);

    /**
     * Method getTravelCarriers returns the travelCarriers of this TravelAgencyService object.
     *
     *
     *
     * @return the travelCarriers (type List<TravelCarrier>) of this TravelAgencyService object.
     */
    List<TravelCarrier> getTravelCarriers();

    /**
     * Method getTravelCarrierById.
     *
     * @param id of type int
     * @return TravelCarrier
     */
    TravelCarrier getTravelCarrierById(int id);

}
