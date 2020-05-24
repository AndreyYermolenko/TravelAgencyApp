package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.RoleDao;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.dto.BranchManagerDto;
import com.yermolenko.model.Role;
import com.yermolenko.model.User;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class UserDAOImpl is designed to receive and modify user data.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Log4j
@Component
public class UserDAOImpl implements UserDAO {

    private final ConnectionPool connectionPool;
    private final RoleDao roleDao;

    /**
     * Constructor UserDAOImpl creates a new UserDAOImpl instance.
     *
     * @param connectionPool of type ConnectionPool
     * @param roleDao
     */
    public UserDAOImpl(ConnectionPool connectionPool, RoleDao roleDao) {
        this.connectionPool = connectionPool;
        this.roleDao = roleDao;
    }

    /**
     * Method registrationUser add user data to database.
     *
     * @param user of type User
     * @return boolean
     */
    @Override
    public boolean registrationUser(User user) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(email, password, first_name, last_name, manager_id, agency_branch_id) " +
                            "VALUES(?, ?, ?, ?, ?, ?); "
            );

            User manager = getManagerByBranchId(user.getAgencyBranchId());

            ps1.setString(1, user.getEmail());
            ps1.setString(2, user.getPassword());
            ps1.setString(3, user.getFirstName());
            ps1.setString(4, user.getLastName());
            ps1.setInt(5, manager.getId());
            ps1.setInt(6, user.getAgencyBranchId());

            PreparedStatement ps2 = connection.prepareStatement(
                    "INSERT INTO role_user VALUES ((SELECT id FROM users WHERE email = ?), 2)"
            );

            ps2.setString(1, user.getEmail());

            int countOfInsert1 = ps1.executeUpdate();
            int countOfInsert2 = ps2.executeUpdate();

            connection.close();

            return (countOfInsert1 != 0 && countOfInsert2 != 0);
        } catch (SQLException ex) {
            log.error("Registration user problem", ex);
        }
        return false;
    }

    /**
     * Method findUserByEmail finds user by email from database.
     *
     * @param email of type String
     * @return User
     */
    @Override
    public User findUserByEmail(String email) {
        Connection connection = connectionPool.getConnection();
        User user = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE email = ?;");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                user = new User();
                mapperUser(user, rs);
            }

            connection.close();
        } catch (SQLException e) {
            log.error("Finding user by email problem", e);
        }

        return user;
    }

    /**
     * Method getUserById gets user by id from database.
     *
     * @param id of type int
     * @return User
     */
    @Override
    public User getUserById(int id) {
        Connection connection = connectionPool.getConnection();
        User user = new User();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            rs.next();
            mapperUser(user, rs);

            connection.close();
        } catch (SQLException ex) {
            log.error("Getting user by id problem", ex);
        }

        return user;
    }

    /**
     * Method getManagerByBranchId.
     *
     * @param id of type int
     * @return User
     */
    @Override
    public User getManagerByBranchId(int id) {
        Connection connection = connectionPool.getConnection();
        Role managerRole = new Role(1, "manager");

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE agency_branch_id = ?");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User user = new User();
                mapperUser(user, rs);
                if (user.getRoles().contains(managerRole)) {
                    connection.close();
                    return user;
                }
            }

            connection.close();
        } catch (SQLException ex) {
            log.error("Getting manager by agency branch id problem", ex);
        }
        return null;
    }

    /**
     * Method rsToUser.
     *
     * @param user of type User
     * @param rs of type ResultSet
     * @return User
     */
    private User mapperUser(User user, ResultSet rs) {
        try {
            user.setId(rs.getInt(1));
            user.setEmail(rs.getString(2));
            user.setPassword(rs.getString(3));
            user.setFirstName(rs.getString(4));
            user.setLastName(rs.getString(5));
            user.setManagerId(rs.getInt(6));
            user.setAgencyBranchId(rs.getInt(7));
            Set<Role> roleSet = roleDao.getRolesByUserId(user.getId());
            user.setRoles(roleSet);
        } catch (SQLException e) {
            log.error("Parsing result set problem", e);
        }
        return user;
    }

}
