package com.yermolenko.services.impl;

import com.yermolenko.dao.TokenDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.Token;
import com.yermolenko.model.User;
import com.yermolenko.services.RestService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Class RestServiceImpl.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Service
public class RestServiceImpl implements RestService {

    private final UserDAO userDAO;

    private final TokenDAO tokenDAO;

    private final PasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor RestServiceImpl creates a new RestServiceImpl instance.
     *  @param userDAO of type UserDAO
     * @param tokenDAO of type TokenDAO
     * @param bCryptPasswordEncoder
     */
    public RestServiceImpl(UserDAO userDAO, TokenDAO tokenDAO, PasswordEncoder bCryptPasswordEncoder) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    /**
     * Method signUp.
     *
     * @param userForm of type UserForm
     * @return boolean
     */
    @Override
    public boolean signUp(UserForm userForm) {
        String hashPassword = bCryptPasswordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(hashPassword)
                .email(userForm.getEmail())
                .build();

        return userDAO.registrationUser(user);
    }

    /**
     * Method login.
     *
     * @param loginForm of type LoginForm
     * @return Token
     */
    @Override
    public Token login(LoginForm loginForm) {
        User userCandidate = userDAO.findUserByEmail(loginForm.getEmail());

        if (userCandidate != null) {
            if (bCryptPasswordEncoder.matches(loginForm.getPassword(), userCandidate.getPassword())) {
                Token token = Token.builder()
                        .user(userCandidate)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenDAO.saveToken(token);
                return token;
            }
        }

        throw new IllegalArgumentException("User not found");
    }

}
