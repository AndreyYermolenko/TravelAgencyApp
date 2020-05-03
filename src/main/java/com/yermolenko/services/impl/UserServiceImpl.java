package com.yermolenko.services.impl;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.User;
import com.yermolenko.services.UserService;
import org.springframework.stereotype.Component;

/**
 * Class UserServiceImpl.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    /**
     * Constructor UserServiceImpl creates a new UserServiceImpl instance.
     *
     * @param userDAO of type UserDAO
     */
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Method registrationUser.
     *
     * @param user of type User
     * @return boolean
     */
    @Override
    public boolean registrationUser(User user) {
        return userDAO.registrationUser(user);
    }

    /**
     * Method findUserByEmail.
     *
     * @param email of type String
     * @return User
     */
    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

}
