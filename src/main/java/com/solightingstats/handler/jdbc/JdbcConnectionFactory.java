package com.solightingstats.handler.jdbc;

import com.solightingstats.environments.DatabaseEnvironments;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcConnectionFactory {
    public static Connection getConnection(DatabaseEnvironments environments) throws SQLException {
        Properties props = new Properties();
        props.setProperty("driver-class-name",environments.getClassName());
        props.setProperty("username",environments.getUsername());
        props.setProperty("password",environments.getPassword());
        
        return DriverManager.getConnection(environments.getUrl(),props);
    }
}

/*
    hsqldb
        Properties props = new Properties();
        props.setProperty("driver-class-name","org.hsqldb.jdbc.JDBCDriver");
        props.setProperty("url","jdbc:hsqldb:hsql://localhost");
        props.setProperty("username","sa");
        props.setProperty("password","");
 */
