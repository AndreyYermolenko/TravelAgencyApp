package com.yermolenko.dao;

import com.yermolenko.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserDAO {

    boolean registrationUser(User user);

    User findUserByEmail(String email);

    User getUserById(int id);

}
