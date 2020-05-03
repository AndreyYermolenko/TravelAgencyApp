package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TokenDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.Token;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class TokenDAOImpl is designed to receive and save token data.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Log4j
@Component
public class TokenDAOImpl implements TokenDAO {

    private final ConnectionPool connectionPool;

    private final UserDAO userDAO;

    /**
     * Constructor TokenDAOImpl creates a new TokenDAOImpl instance.
     *
     * @param connectionPool of type ConnectionPool
     * @param userDAO of type UserDAO
     */
    public TokenDAOImpl(ConnectionPool connectionPool, UserDAO userDAO) {
        this.connectionPool = connectionPool;
        this.userDAO = userDAO;
    }

    /**
     * Method findOneByValue gets from the database token data.
     *
     * @param value of type String
     * @return Token
     */
    @Override
    public Token findOneByValue(String value) {
        Connection connection = connectionPool.getConnection();
        Token token = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tokens " +
                    "WHERE token = ?");
            ps.setString(1, value);

            ResultSet rs = ps.executeQuery();
            rs.next();
            token = Token.builder()
                    .id(rs.getInt(1))
                    .value(rs.getString(2))
                    .user(userDAO.getUserById(rs.getInt(3)))
                    .build();

            connection.close();
        } catch (SQLException ex) {
            log.error("Finding token problem", ex);
        }

        return token;
    }

    /**
     * Method saveToken saves to the database token data.
     *
     * @param token of type Token
     * @return boolean
     */
    @Override
    public boolean saveToken(Token token) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO tokens " +
                            "(token, user_id) " +
                            "VALUES(?, ?); "
            );

            ps1.setString(1, token.getValue());
            ps1.setInt(2, token.getUser().getId());

            int countOfInsert = ps1.executeUpdate();

            connection.close();

            return countOfInsert != 0;
        } catch (SQLException ex) {
            log.error("Saving token problem", ex);
        }
        return false;
    }
}
