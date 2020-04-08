package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.SearchTourParams;
import com.yermolenko.model.TravelTour;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAOImpl implements UserDAO {

    private final ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public ArrayList<TravelTour> getAllTours() {
        Connection connection = connectionPool.getConnection();

        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM travel_tour");

            while(rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
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

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM travel_tour " +
                    "WHERE destination = ? AND begin_date >= ? AND end_date <= ? AND cost BETWEEN ? AND ?");
            ps.setString(1, destination);
            ps.setDate(2, java.sql.Date.valueOf(beginDate));
            ps.setDate(3, java.sql.Date.valueOf(endDate));
            ps.setFloat(4, minCost);
            ps.setFloat(5, maxCost);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                TravelTour tour = new TravelTour();

                tour.setId(rs.getInt(1));
                tour.setDestination(rs.getString(2));
                tour.setBeginDate(rs.getDate(3).toLocalDate());
                tour.setEndDate(rs.getDate(4).toLocalDate());
                tour.setCost(rs.getFloat(5));
                tour.setMaxCount(rs.getInt(6));
                tour.setCurrentCount(rs.getInt(7));
                tour.setDescription(rs.getString(8));

                tours.add(tour);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return tours;
    }

    @Override
    public boolean buyTour(TravelTour travelTour) {
        return false;
    }

    @Override
    public boolean updateTour(TravelTour travelTour) {
        return false;
    }

    @Override
    public boolean deleteTour(TravelTour travelTour) {
        return false;
    }

    @Override
    public boolean addTour(TravelTour travelTour) {
        return false;
    }
}
