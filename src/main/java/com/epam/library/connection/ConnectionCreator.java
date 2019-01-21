package com.epam.library.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {
    private static final String DB_DRIVER = "driver";
    private static final String DB_URL = "url";
    private static final String DB_USER = "name";
    private static final String DB_PASS = "password";


    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {
        PropertiesReader propertiesReader = new PropertiesReader();
        Properties properties = propertiesReader.getProperty();

        String dbDriver = properties.getProperty(DB_DRIVER);
        String dbUrl = properties.getProperty(DB_URL);
        String dbUser = properties.getProperty(DB_USER);
        String dbPassword = properties.getProperty(DB_PASS);

        Class.forName(dbDriver);
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }
}
