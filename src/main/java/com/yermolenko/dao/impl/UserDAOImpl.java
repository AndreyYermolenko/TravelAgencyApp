package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.Role;
import com.yermolenko.model.User;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Component
public class UserDAOImpl implements UserDAO {

    private final ConnectionPool connectionPool;

    public UserDAOImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    @Override
    public void registrationUser(User user) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO users " +
                            "(email, password, first_name, last_name, manager_id) " +
                            "VALUES(?, ?, ?, ?, 1); "
            );

            ps1.setString(1, user.getEmail());
            ps1.setString(2, user.getPassword());
            ps1.setString(3, user.getFirstName());
            ps1.setString(4, user.getLastName());

            PreparedStatement ps2 = connection.prepareStatement(
                    "INSERT INTO role_user VALUES ((SELECT id FROM users WHERE email = ?), 2)"
            );

            ps2.setString(1, user.getEmail());

            ps1.executeUpdate();
            ps2.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public User findUserByEmail(String email) {
        Connection connection = connectionPool.getConnection();
        User user = new User();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM users " +
                    "WHERE email = ?;");
            ps.setString(1, email);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                user.setId(rs.getInt(1));
                user.setEmail(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setManagerId(rs.getInt(6));
                user.setRoles(getAuthoritiesById(rs.getInt(1)));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    private Set<Role> getAuthoritiesById(int id) {
        Connection connection = connectionPool.getConnection();
        Set<Role> authorities = new HashSet<>();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM role_user " +
                    "WHERE user_id = ?;");
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                authorities.add(getRoleById(rs.getInt(2)));
            }

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return authorities;
    }

    private Role getRoleById(int roleId) {
        Connection connection = connectionPool.getConnection();
        Role role = new Role();

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM roles " +
                    "WHERE id = ?;");
            ps.setInt(1, roleId);

            ResultSet rs = ps.executeQuery();
            rs.next();
            role.setId(rs.getInt(1));
            role.setName(rs.getString(2));

            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }
}
