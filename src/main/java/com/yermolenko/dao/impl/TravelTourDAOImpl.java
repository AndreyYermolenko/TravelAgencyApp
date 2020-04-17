package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TravelTourDAOImpl implements TravelTourDAO {

    private final ConnectionPool connectionPool;

    public TravelTourDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public List<TravelTour> getAllTours(SearchTourParams searchTourParams) {
        List<TravelTour> tours = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try {
            String desc = "";
            String sortedBy = searchTourParams.getSortedBy();

            if (searchTourParams.getDesc()) {
                desc = "DESC";
            }

            PreparedStatement ps = connection.prepareStatement(
                    "SELECT * FROM travel_tour ORDER BY "
                            + sortedBy + " "
                            + desc + ";");
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                TravelTour tour = new TravelTour();
                tour = rsToTravelTour(tour, rs);
                tours.add(tour);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tours;
    }

    @Override
    public List<TravelTour> getSomeTours(SearchTourParams searchTourParams) {
        List<TravelTour> tours = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        String destination = searchTourParams.getDestination();
        LocalDate beginDate = searchTourParams.getBeginDate();
        LocalDate endDate = searchTourParams.getEndDate();
        Float minCost = searchTourParams.getMinCost();
        Float maxCost = searchTourParams.getMaxCost();

        if (beginDate == null) {
            beginDate = LocalDate.now();
        }
        if (endDate == null) {
            endDate = LocalDate.now().plusYears(2);
        }
        if (minCost == null) {
            minCost = 0.0f;
        }
        if (maxCost == null) {
            maxCost = Float.MAX_VALUE;
        }

        String desc = "";
        String sortedBy = searchTourParams.getSortedBy();

        if (searchTourParams.getDesc()) {
            desc = "DESC";
        }


        try {
            PreparedStatement ps;

            if ("".equals(destination)) {
                ps = connection.prepareStatement("SELECT * FROM travel_tour " +
                        "WHERE begin_date >= ? AND end_date <= ? AND cost BETWEEN ? AND ?" +
                        "ORDER BY " + sortedBy + " " + desc + ";"
                );
                ps.setDate(1, java.sql.Date.valueOf(beginDate));
                ps.setDate(2, java.sql.Date.valueOf(endDate));
                ps.setFloat(3, minCost);
                ps.setFloat(4, maxCost);
            } else {
                ps = connection.prepareStatement("SELECT * FROM travel_tour " +
                        "WHERE destination = ? AND begin_date >= ? AND end_date <= ? AND cost BETWEEN ? AND ?" +
                        "ORDER BY " + sortedBy + " " + desc + ";"
                );
                ps.setString(1, destination);
                ps.setDate(2, java.sql.Date.valueOf(beginDate));
                ps.setDate(3, java.sql.Date.valueOf(endDate));
                ps.setFloat(4, minCost);
                ps.setFloat(5, maxCost);
            }

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TravelTour tour = new TravelTour();
                tour = rsToTravelTour(tour, rs);
                tours.add(tour);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tours;
    }

    @Override
    public TravelTour getTour(int id) {
        Connection connection = connectionPool.getConnection();
        TravelTour tour = new TravelTour();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM travel_tour " +
                    "WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            tour = rsToTravelTour(tour, rs);

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return tour;
    }

    private TravelTour rsToTravelTour(TravelTour tour, ResultSet rs) {
        try {
            tour.setId(rs.getInt(1));
            tour.setDestination(rs.getString(2));
            tour.setBeginDate(rs.getDate(3).toLocalDate());
            tour.setEndDate(rs.getDate(4).toLocalDate());
            tour.setCost(rs.getFloat(5));
            tour.setMaxCount(rs.getInt(6));
            tour.setCurrentCount(rs.getInt(7));
            tour.setDescription(rs.getString(8));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tour;
    }

    @Override
    public boolean reservationTour(TravelTour travelTour) {
        return false;
    }

    @Override
    public void updateTour(int id, TravelTour travelTour) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE travel_tour SET " +
                    "destination = ?, " +
                    "begin_date = ?, " +
                    "end_date = ?, " +
                    "cost = ?, " +
                    "max_count = ?, " +
                    "description = ? " +
                    "WHERE id = ?");
            ps.setString(1, travelTour.getDestination());
            ps.setDate(2, java.sql.Date.valueOf(travelTour.getBeginDate()));
            ps.setDate(3, java.sql.Date.valueOf(travelTour.getEndDate()));
            ps.setFloat(4, travelTour.getCost());
            ps.setInt(5, travelTour.getMaxCount());
            ps.setString(6, travelTour.getDescription());
            ps.setInt(7, id);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void deleteTour(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement("DELETE FROM travel_tour " +
                    "WHERE id = ?");
            ps.setInt(1, id);

            ps.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void addTour(TravelTour travelTour) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps = connection.prepareStatement(
                    "INSERT INTO travel_tour " +
                        "(destination, begin_date, end_date, cost, max_count, current_count, description) " +
                        "VALUES(?, ?, ?, ?, ?, 0, ?);"
            );
            ps.setString(1, travelTour.getDestination());
            ps.setDate(2, java.sql.Date.valueOf(travelTour.getBeginDate()));
            ps.setDate(3, java.sql.Date.valueOf(travelTour.getEndDate()));
            ps.setFloat(4, travelTour.getCost());
            ps.setInt(5, travelTour.getMaxCount());
            ps.setString(6, travelTour.getDescription());

            ps.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
