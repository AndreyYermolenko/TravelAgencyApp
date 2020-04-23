package com.yermolenko.dao;

import com.yermolenko.model.User;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public interface UserDAO {

    void registrationUser(User user);

    User findUserByEmail(String email);

    User getUserById(int id);

}
