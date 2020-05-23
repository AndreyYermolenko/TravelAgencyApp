package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TravelCarrierDAO;
import com.yermolenko.model.TravelCarrier;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Log4j
@Component
public class TravelCarrierDAOImpl implements TravelCarrierDAO {

    private final ConnectionPool connectionPool;

    public TravelCarrierDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Method getTravelCarriers returns the travelCarriers of this TravelAgencyDAOImpl object.
     *
     *
     *
     * @return the travelCarriers (type List<TravelCarrier>) of this TravelAgencyDAOImpl object.
     */
    @Override
    public List<TravelCarrier> getTravelCarriers() {
        List<TravelCarrier> travelCarriers = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM travel_carrier;"
            );
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                TravelCarrier travelCarrier = new TravelCarrier();
                travelCarrier.setId(rs.getInt(1));
                travelCarrier.setDestination(rs.getString(2));
                travelCarriers.add(travelCarrier);
            }
            connection.close();

        } catch (SQLException e) {
            log.error("Getting travel carriers problem", e);
        }

        return travelCarriers;
    }

    /**
     * Method getTravelCarrierById.
     *
     * @param id of type int
     * @return TravelCarrier
     */
    @Override
    public TravelCarrier getTravelCarrierById(int id) {
        TravelCarrier travelCarrier = new TravelCarrier();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM travel_carrier WHERE id = ?;"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            travelCarrier.setId(rs.getInt(1));
            travelCarrier.setDestination(rs.getString(2));

            connection.close();

        } catch (SQLException e) {
            log.error("Getting travel carrier problem", e);
        }

        return travelCarrier;
    }
}
