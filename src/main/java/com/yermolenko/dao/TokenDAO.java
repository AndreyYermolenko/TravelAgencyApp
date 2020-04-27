package com.yermolenko.dao;

import com.yermolenko.model.Token;

public interface TokenDAO {

    Token findOneByValue(String value);

    void saveToken(Token token);

}
