package com.yermolenko.dao.impl;

import com.yermolenko.dao.AgencyBranchDAO;
import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.model.AgencyBranch;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class AgencyBranchDAOImpl.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
@Log4j
@Component
public class AgencyBranchDAOImpl implements AgencyBranchDAO {

    private final ConnectionPool connectionPool;

    /**
     * Constructor AgencyBranchDAOImpl creates a new AgencyBranchDAOImpl instance.
     *
     * @param connectionPool of type ConnectionPool
     */
    public AgencyBranchDAOImpl(ConnectionPool connectionPool) {
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
}
