package com.yermolenko.services.impl;

import com.yermolenko.dao.TokenDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.Token;
import com.yermolenko.model.User;
import com.yermolenko.services.RestService;
import com.yermolenko.dto.TokenDto;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import static com.yermolenko.dto.TokenDto.from;

@Service
public class RestServiceImpl implements RestService {

    private final UserDAO userDAO;

    private final TokenDAO tokenDAO;

    public RestServiceImpl(UserDAO userDAO, TokenDAO tokenDAO) {
        this.userDAO = userDAO;
        this.tokenDAO = tokenDAO;
    }

    @Override
    public void signUp(UserForm userForm) {
        String password = userForm.getPassword();


        User user = User.builder()
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .password(password)
                .email(userForm.getEmail())
                .build();

        userDAO.registrationUser(user);
    }

    @Override
    public TokenDto login(LoginForm loginForm) {
        User userCandidate = userDAO.findUserByEmail(loginForm.getEmail());

        if (userCandidate != null) {
            if (userCandidate.getPassword().equals(loginForm.getPassword())) {
                Token token = Token.builder()
                        .user(userCandidate)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokenDAO.saveToken(token);
                return from(token);
            }
        }

        throw new IllegalArgumentException("User not found");
    }

}
