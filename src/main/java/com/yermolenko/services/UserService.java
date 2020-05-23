package com.yermolenko.services;

import com.yermolenko.dto.BranchManagerDto;
import com.yermolenko.model.User;

import java.util.List;

/**
 * Interface UserService.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface UserService {

    /**
     * Method registrationUser.
     *
     * @param user of type User
     * @return boolean
     */
    boolean registrationUser(User user);

    /**
     * Method findUserByEmail.
     *
     * @param email of type String
     * @return User
     */
    User findUserByEmail(String email);

    List<BranchManagerDto> getBranchManagerStat();

}
