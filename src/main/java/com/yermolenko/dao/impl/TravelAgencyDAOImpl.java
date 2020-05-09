package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TravelAgencyDAO;
import com.yermolenko.model.AgencyBranch;
import com.yermolenko.model.Resort;
import com.yermolenko.model.TravelCarrier;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class TravelAgencyDAOImpl.
 *
 * @author Andrey
 * Created on 09.05.2020
 */
@Log4j
@Component
public class TravelAgencyDAOImpl implements TravelAgencyDAO {

    private final ConnectionPool connectionPool;

    /**
     * Constructor TravelAgencyDAOImpl creates a new TravelAgencyDAOImpl instance.
     *
     * @param connectionPool of type ConnectionPool
     */
    public TravelAgencyDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Method getAgencyBranches returns the agencyBranches of this TravelAgencyDAOImpl object.
     *
     *
     *
     * @return the agencyBranches (type List<AgencyBranch>) of this TravelAgencyDAOImpl object.
     */
    @Override
    public List<AgencyBranch> getAgencyBranches() {
        List<AgencyBranch> branches = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM agency_branch;"
            );
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                AgencyBranch agencyBranch = new AgencyBranch();
                agencyBranch.setId(rs.getInt(1));
                agencyBranch.setDestination(rs.getString(2));
                agencyBranch.setAddress(rs.getString(3));
                branches.add(agencyBranch);
            }
            connection.close();

        } catch (SQLException e) {
            log.error("Getting agency branches problem", e);
        }

        return branches;
    }

    /**
     * Method getAgencyBranchById.
     *
     * @param id of type int
     * @return AgencyBranch
     */
    @Override
    public AgencyBranch getAgencyBranchById(int id) {
        AgencyBranch agencyBranch = new AgencyBranch();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM agency_branch WHERE id = ?;"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            agencyBranch.setId(rs.getInt(1));
            agencyBranch.setDestination(rs.getString(2));
            agencyBranch.setAddress(rs.getString(3));

            connection.close();

        } catch (SQLException e) {
            log.error("Getting agency branch problem", e);
        }

        return agencyBranch;
    }

    /**
     * Method getResorts returns the resorts of this TravelAgencyDAOImpl object.
     *
     *
     *
     * @return the resorts (type List<Resort>) of this TravelAgencyDAOImpl object.
     */
    @Override
    public List<Resort> getResorts() {
        List<Resort> resorts = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM resort;"
            );
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                Resort resort = new Resort();
                resort.setId(rs.getInt(1));
                resort.setDestination(rs.getString(2));
                resort.setCountry(rs.getString(3));
                resort.setDescription(rs.getString(4));
                resorts.add(resort);
            }
            connection.close();

        } catch (SQLException e) {
            log.error("Getting resorts problem", e);
        }

        return resorts;
    }

    /**
     * Method getResortById.
     *
     * @param id of type int
     * @return Resort
     */
    @Override
    public Resort getResortById(int id) {
        Resort resort = new Resort();
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM resort WHERE id = ?;"
            );
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            resort.setId(rs.getInt(1));
            resort.setDestination(rs.getString(2));
            resort.setCountry(rs.getString(3));
            resort.setDescription(rs.getString(4));

            connection.close();

        } catch (SQLException e) {
            log.error("Getting resort problem", e);
        }

        return resort;
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
