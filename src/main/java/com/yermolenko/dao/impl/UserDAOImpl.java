package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.TravelTour;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
    public ArrayList<TravelTour> getSomeTours() {

        return null;
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
