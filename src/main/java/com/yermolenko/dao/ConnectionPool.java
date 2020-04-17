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
    private static DataSource ds;

//    TomCat Datasource
    private static PoolProperties p = new PoolProperties();
    static {
        p.setUrl("jdbc:postgresql://localhost:5432/travel_agency");
        p.setDriverClassName("org.postgresql.Driver");
        p.setUsername("postgres");
        p.setPassword("root");
        ds = new org.apache.tomcat.jdbc.pool.DataSource(p);
    }

    //WebLogic Datasource
//    static {
//        Context context;
//        try {
//            context = new InitialContext();
//            ds = (DataSource) context.lookup("jdbc/postgres");
//        } catch (NamingException e) {
//            e.printStackTrace();
//        }
//    }

    public Connection getConnection(){
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
