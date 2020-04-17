package com.yermolenko.services.impl;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.User;
import com.yermolenko.services.UserService;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public void registrationUser(User user) {
        userDAO.registrationUser(user);
    }

    @Override
    public User findUserByEmail(String email) {
        return userDAO.findUserByEmail(email);
    }

}
