package com.yermolenko.dao;

import org.apache.tomcat.jdbc.pool.PoolProperties;
import org.springframework.stereotype.Component;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class ConnectionPool {

    public Connection getConnection(){
        PoolProperties p = new PoolProperties();
        p.setUrl("jdbc:postgresql://localhost:5432/travel_agency");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("root");
        DataSource ds = new org.apache.tomcat.jdbc.pool.DataSource(p);

        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

//    public Connection getConnection(){
//        Context context;
//        Connection connection = null;
//        try {
//            context = new InitialContext();
//            DataSource ds = (DataSource) context.lookup("jdbc/postgres"); //поменять jndi в datasource
//            connection = ds.getConnection();
//        } catch (NamingException | SQLException e) {
//            e.printStackTrace();
//        }
//        return connection;
//    }
}
