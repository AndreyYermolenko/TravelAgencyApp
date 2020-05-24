package com.yermolenko.dao;

import com.yermolenko.dto.BranchManagerDto;
import com.yermolenko.model.User;

import java.util.List;

/**
 * Interface UserDAO is designed to receive and modify user data.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface UserDAO {

    /**
     * Method registrationUser add user data to database.
     *
     * @param user of type User
     * @return boolean
     */
    boolean registrationUser(User user);

    /**
     * Method findUserByEmail finds user by email from database.
     *
     * @param email of type String
     * @return User
     */
    User findUserByEmail(String email);

    /**
     * Method getUserById gets user by id from database.
     *
     * @param id of type int
     * @return User
     */
    User getUserById(int id);

    /**
     * Method getManagerByBranchId.
     *
     * @param id of type int
     * @return User
     */
    User getManagerByBranchId(int id);
}
