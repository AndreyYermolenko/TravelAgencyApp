package com.yermolenko.services;

import com.yermolenko.forms.LoginForm;
import com.yermolenko.forms.UserForm;
import com.yermolenko.transfer.TokenDto;

public interface RestService {

    void signUp(UserForm userForm);

    TokenDto login(LoginForm loginForm);
}