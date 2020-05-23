package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.RoleDao;
import com.yermolenko.model.Role;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

@Log4j
@Component
public class RoleDaoImpl implements RoleDao {

    private final ConnectionPool connectionPool;

    public RoleDaoImpl(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    /**
     * Method getAuthoritiesById.
     *
     * @param id of type int
     * @return Set<Role>
     */
    @Override
    public Set<Role> getRolesByUserId(int id) {
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
            log.error("Getting authorities by id problem", e);
        }

        return authorities;
    }

    /**
     * Method getRoleById.
     *
     * @param roleId of type int
     * @return Role
     */
    @Override
    public Role getRoleById(int roleId) {
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
            log.error("Getting role by id problem", e);
        }
        return role;
    }
}
