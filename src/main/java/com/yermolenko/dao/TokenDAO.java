package com.yermolenko.dao;

import com.yermolenko.model.Token;

public interface TokenDAO {

    Token findOneByValue(String value);

    boolean saveToken(Token token);

}
