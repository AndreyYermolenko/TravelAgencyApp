package com.yermolenko.dao.impl;

import com.yermolenko.dao.ConnectionPool;
import com.yermolenko.dao.TokenDAO;
import com.yermolenko.dao.UserDAO;
import com.yermolenko.model.Token;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class TokenDAOImpl implements TokenDAO {

    private final ConnectionPool connectionPool;

    private final UserDAO userDAO;

    public TokenDAOImpl(ConnectionPool connectionPool, UserDAO userDAO) {
        this.connectionPool = connectionPool;
        this.userDAO = userDAO;
    }

    @Override
    public Token findOneByValue(String value) {
        Connection connection = connectionPool.getConnection();
        Token token = null;

        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM tokens " +
                    "WHERE value = ?");
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
            ex.printStackTrace();
        }

        return token;
    }

    @Override
    public void saveToken(Token token) {
        Connection connection = connectionPool.getConnection();

        try {
            PreparedStatement ps1 = connection.prepareStatement(
                    "INSERT INTO tokens " +
                            "(token, user_id) " +
                            "VALUES(?, ?); "
            );

            ps1.setString(1, token.getValue());
            ps1.setInt(2, token.getUser().getId());

            ps1.executeUpdate();

            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
