package com.yermolenko.security.details;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User userCandidate = userDAO.findUserByEmail(login);
        if (userCandidate != null) {
            return new UserDetailsImpl(userCandidate);
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}
