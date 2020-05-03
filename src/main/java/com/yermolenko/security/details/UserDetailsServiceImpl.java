package com.yermolenko.security.details;

import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * User Details Service.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    /**
     * Constructor UserDetailsServiceImpl creates a new UserDetailsServiceImpl instance.
     *
     * @param userDAO of type UserDAO
     */
    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    /**
     * Method loadUserByUsername returns user object by username.
     *
     * @param login of type String
     * @return UserDetails
     * @throws UsernameNotFoundException when
     */
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
