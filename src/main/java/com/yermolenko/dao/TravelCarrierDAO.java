package com.yermolenko.dao;

import com.yermolenko.model.TravelCarrier;

import java.util.List;

public interface TravelCarrierDAO {

    List<TravelCarrier> getTravelCarriers();

    TravelCarrier getTravelCarrierById(int id);

}
