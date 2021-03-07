package com.yermolenko.dao;

import lombok.extern.log4j.Log4j;
import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

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

    private static final DataSource ds;
    private static final PoolProperties p = new PoolProperties();
    private static String dbUrl;
    private static String dbDriverClassName;
    private static String dbUsername;
    private static String dbPassword;

    static {
        try (InputStream in = ConnectionPool.class
                .getClassLoader().getResourceAsStream("application.properties")) {
            Properties properties = new Properties();
            properties.load(in);
            dbUrl = properties.getProperty("db.url");
            dbDriverClassName = properties.getProperty("db.driverClassName");
            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");
        } catch (IOException e) {
            log.error("Problem with getting DB credential from apllication.properties", e);
        }
        p.setUrl(dbUrl);
        p.setDriverClassName(dbDriverClassName);
        p.setUsername(dbUsername);
        p.setPassword(dbPassword);
        ds = new org.apache.tomcat.jdbc.pool.DataSource(p);
    }

    @Bean
    public DataSource dataSource() {
        return ds;
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
            connection = ds.getConnection();
        } catch (SQLException e) {
            log.error("Getting connection problem", e);
        }
        return connection;
    }
}
