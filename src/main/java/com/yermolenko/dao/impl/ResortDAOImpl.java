package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.ResortDAO;
import com.yermolenko.model.Resort;
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
public class ResortDAOImpl implements ResortDAO {

    private final ConnectionPool connectionPool;

    public ResortDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
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
}
