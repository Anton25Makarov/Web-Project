package com.epam.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionCreator {
    public static final String URL = "jdbc:mysql://localhost:3306/library";
    public static final String USER = "root";
    public static final String PASS = "password";



    public static Connection getConnection()
    {
        try {
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }
}
