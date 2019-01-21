package com.epam.library.connection;

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
        InputStream inputStream = ConnectionCreator.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);

        properties.load(inputStream);

        return properties;
    }
}
