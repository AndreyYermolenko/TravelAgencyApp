package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TravelTourDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.forms.SearchTourParams;
import com.yermolenko.model.TravelTour;
import com.yermolenko.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class TravelTourDAOImpl implements TravelTourDAO {

    private final ConnectionPool connectionPool;
    private final UserDAO userDAO;

    public TravelTourDAOImpl(ConnectionPool connectionPool, UserDAO userDAO) {
        this.connectionPool = connectionPool;
        this.userDAO = userDAO;
    }

    @Override
    public List<TravelTour> getAllTours(SearchTourParams searchTourParams) {
        List<TravelTour> tours = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try {
            String desc = "";
            String sortedBy;
            if (searchTourParams.getSortedBy() == null) {
                sortedBy = "destination";
            } else {
                sortedBy = searchTourParams.getSortedBy();
            }

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
        String sortedBy;
        if (searchTourParams.getSortedBy() == null) {
            sortedBy = "destination";
        } else {
            sortedBy = searchTourParams.getSortedBy();
        }

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
                        "WHERE destination LIKE ? AND begin_date >= ? AND end_date <= ? AND cost BETWEEN ? AND ?" +
                        "ORDER BY " + sortedBy + " " + desc + ";"
                );
                ps.setString(1, destination + '%');
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
    public TravelTour getTourById(int id) {
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

    @Override
    public boolean reservationTour(User user, TravelTour travelTour) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement(
                    "SELECT count(*) FROM tour_user " +
                            "WHERE user_id = ? AND tour_id = ?;"
            );
            ps1.setInt(1, user.getId());
            ps1.setInt(2, travelTour.getId());
            ResultSet rs1 = ps1.executeQuery();
            rs1.next();
            int countOfReservation = rs1.getInt(1);
            if (countOfReservation != 0) {
                return false;
            }

            PreparedStatement ps2 = connection.prepareStatement(
                    "SELECT current_count, max_count FROM travel_tour " +
                            "WHERE id = ?;"
            );
            ps2.setInt(1, travelTour.getId());
            ResultSet rs2 = ps2.executeQuery();
            rs2.next();
            int currentCountOfUsers = rs2.getInt(1);
            int maxCountOfUsers = rs2.getInt(2);
            if (currentCountOfUsers >= maxCountOfUsers) {
                return false;
            }

            PreparedStatement ps3 = connection.prepareStatement(
                    "UPDATE travel_tour SET current_count = ? " +
                            "WHERE id = ?;"
            );
            ps3.setInt(1, ++currentCountOfUsers);
            ps3.setInt(2, travelTour.getId());
            ps3.executeUpdate();

            PreparedStatement ps4 = connection.prepareStatement(
                    "INSERT INTO tour_user " +
                            "(user_id, tour_id) " +
                            "VALUES(?, ?);"
            );
            ps4.setInt(1, user.getId());
            ps4.setInt(2, travelTour.getId());

            int countOfInsert = ps4.executeUpdate();
            connection.close();

            return countOfInsert != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public List<TravelTour> getReservedTours(User user) {
        List<TravelTour> tours = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        int userId = user.getId();

        try {
            PreparedStatement ps;

            ps = connection.prepareStatement("SELECT * FROM tour_user " +
                    "WHERE user_id = ?;"
            );
            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int tourId = rs.getInt(2);
                TravelTour tour = getTourById(tourId);
                tours.add(tour);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
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
    public boolean updateTour(int id, TravelTour travelTour) {
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

            int countOfUpdate = ps.executeUpdate();

            connection.close();

            return countOfUpdate != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteTour(int id) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement("DELETE FROM tour_user " +
                    "WHERE tour_id = ?");
            ps1.setInt(1, id);

            PreparedStatement ps2 = connection.prepareStatement("DELETE FROM travel_tour " +
                    "WHERE id = ?");
            ps2.setInt(1, id);

            int countOfDelete1 =  ps1.executeUpdate();
            int countOfDelete2 = ps2.executeUpdate();

            connection.close();

            return (countOfDelete1 != 0 && countOfDelete2 != 0);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean addTour(TravelTour travelTour) {
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

            int countOfInsert = ps.executeUpdate();

            connection.close();

            return countOfInsert != 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> getListOfReservedTourUsers(TravelTour tour) {
        List<User> tours = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        int tourId = tour.getId();

        try {
            PreparedStatement ps;

            ps = connection.prepareStatement("SELECT * FROM tour_user " +
                    "WHERE tour_id = ?;"
            );
            ps.setInt(1, tourId);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int userId = rs.getInt(1);
                User user = userDAO.getUserById(userId);
                tours.add(user);
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tours;
    }

}
