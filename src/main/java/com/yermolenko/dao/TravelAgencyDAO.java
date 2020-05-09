package com.yermolenko.dao;

import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;

import java.util.List;

/**
 * Interface TravelAgencyDAO.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
public interface TravelAgencyDAO {

    /**
     * Method getAgencyBranches returns the agencyBranches of this TravelAgencyDAO object.
     *
     *
     *
     * @return the agencyBranches (type List<AgencyBranch>) of this TravelAgencyDAO object.
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
     * Method getResorts returns the resorts of this TravelAgencyDAO object.
     *
     *
     *
     * @return the resorts (type List<Resort>) of this TravelAgencyDAO object.
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
     * Method getTravelCarriers returns the travelCarriers of this TravelAgencyDAO object.
     *
     *
     *
     * @return the travelCarriers (type List<TravelCarrier>) of this TravelAgencyDAO object.
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
