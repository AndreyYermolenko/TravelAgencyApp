package com.yermolenko.dao;

import com.yermolenko.model.Role;

import java.util.Set;

public interface RoleDao {

    Set<Role> getRolesByUserId(int userId);

    Role getRoleById(int roleId);

}
