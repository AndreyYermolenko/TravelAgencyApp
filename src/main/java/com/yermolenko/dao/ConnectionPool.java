package com.yermolenko.dao;

import com.yermolenko.exceptions.ConnectionToDatabaseException;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Class ConnectionPool contains connection pool.
 *
 * @author Andrey
 * Created on 03.05.2020
 */
@Log4j
@Component
public class ConnectionPool {

    private String datasource;

    private DataSource ds;

    {
        try(InputStream in = ConnectionPool.class
                .getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            datasource = properties.getProperty("datasource.name");
        } catch (IOException e) {
            log.error("Getting datasource problem", e);
        }
    }

    {
        Context context;
        try {
            context = new InitialContext();
            ds = (DataSource) context.lookup(datasource);
        } catch (NamingException e) {
            log.error("Getting datasource name problem", e);
        }
    }

    /**
     * Method getConnection returns the connection of this ConnectionPool object.
     *
     *
     *
     * @return the connection (type Connection) of this ConnectionPool object.
     */
    public Connection getConnection(){
        Connection connection = null;
        try {
            if (ds == null) {
                throw new ConnectionToDatabaseException();
            }
            connection = ds.getConnection();
        } catch (SQLException e) {
            log.error("Getting connection problem", e);
        }
        return connection;
    }


}
