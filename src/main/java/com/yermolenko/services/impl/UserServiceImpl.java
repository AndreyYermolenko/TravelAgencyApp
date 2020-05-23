package com.yermolenko.services.impl;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.dto.BranchManagerDto;
import com.yermolenko.model.User;
import com.yermolenko.services.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Class UserServiceImpl.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Component
public class UserServiceImpl implements UserService {

    private final UserDAO userDAO;

    private final PasswordEncoder bCryptPasswordEncoder;


    /**
     * Constructor UserServiceImpl creates a new UserServiceImpl instance.
     *
     * @param userDAO of type UserDAO
     */
    public UserServiceImpl(UserDAO userDAO, PasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Method registrationUser.
     *
     * @param user of type User
     * @return boolean
     */
    @Override
    public boolean registrationUser(User user) {
        String hashPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
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

    @Override
    public List<BranchManagerDto> getBranchManagerStat() {
        return userDAO.getBranchManagerStat();
    }

}
