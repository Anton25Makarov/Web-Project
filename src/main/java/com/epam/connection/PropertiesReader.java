package com.epam.connection;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {
    private static final String PROPERTIES_FILE = "dataBaseProperties.properties";

    private Properties properties;

    public PropertiesReader() {
        this.properties = new Properties();
    }

    public Properties getProperty() throws IOException {
        try {
        InputStream inputStream = ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
//        System.out.printf("1))))): " + ConnectionCreator.class);
//        System.out.printf("2))))): " + ConnectionCreator.class.getResourceAsStream(PROPERTIES_FILE));
        /*if (inputStream == null) {
            System.out.printf("Cannot read properties file to connect to db");
            throw new IOException("Cannot read properties file to connect to db");
        }*/
            properties.load(inputStream);
        } catch (IOException e) {
            throw new IOException("Cannot read properties file to connect to db", e);
        }
        return properties;
    }
}
