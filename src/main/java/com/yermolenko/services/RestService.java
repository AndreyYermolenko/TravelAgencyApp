package com.yermolenko.services;

import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.model.Token;

/**
 * Interface RestService.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface RestService {

    /**
     * Method signUp.
     *
     * @param userForm of type UserForm
     * @return boolean
     */
    boolean signUp(UserForm userForm);

    /**
     * Method login.
     *
     * @param loginForm of type LoginForm
     * @return TokenDto
     */
    Token login(LoginForm loginForm);
}
