package com.yermolenko.dao;

import com.yermolenko.model.Role;

import java.util.Set;

/**
 * Interface RoleDao.
 *
 * @author Andrey
 * Created on 24.05.2020
 */
public interface RoleDAO {

    /**
     * Method getRolesByUserId.
     *
     * @param userId of type int
     * @return Set<Role>
     */
    Set<Role> getRolesByUserId(int userId);

    /**
     * Method getRoleById.
     *
     * @param roleId of type int
     * @return Role
     */
    Role getRoleById(int roleId);

}
