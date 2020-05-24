package com.yermolenko.dao;

import com.yermolenko.model.TravelCarrier;

import java.util.List;

/**
 * Interface TravelCarrierDAO.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
public interface TravelCarrierDAO {

    /**
     * Method getTravelCarriers returns the travelCarriers of this TravelCarrierDAO object.
     *
     *
     *
     * @return the travelCarriers (type List<TravelCarrier>) of this TravelCarrierDAO object.
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
