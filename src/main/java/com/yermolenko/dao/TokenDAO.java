package com.yermolenko.dao;

import com.yermolenko.model.Token;

/**
 * Interface TokenDAO is designed to receive and save token data.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
public interface TokenDAO {

    /**
     * Method findOneByValue gets from the database token data.
     *
     * @param value of type String
     * @return Token
     */
    Token findOneByValue(String value);

    /**
     * Method saveToken saves to the database token data.
     *
     * @param token of type Token
     * @return boolean
     */
    boolean saveToken(Token token);

}
