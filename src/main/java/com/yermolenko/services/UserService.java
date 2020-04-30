package com.yermolenko.services;

import com.yermolenko.model.User;

public interface UserService {

    boolean registrationUser(User user);

    User findUserByEmail(String email);

}
