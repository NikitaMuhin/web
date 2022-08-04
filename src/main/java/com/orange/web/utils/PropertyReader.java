package com.orange.web.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertyReader {

    public static final String POSTGRE_DRIVER = "POSTGRE_DRIVER";
    public static final String JDBC_URL = "JDBC_URL";
    public static final String DATABASE_NAME = "DATABASE_NAME";
    public static final String DATABASE_USERNAME = "DATABASE_USERNAME";
    public static final String DATABASE_PASSWORD = "DATABASE_PASSWORD";
    public static final String HOST = "HOST";

    private static final Properties properties = loadProperties();
    private static Properties loadProperties() {

        Properties properties = new Properties();

        try {
            properties.load(new FileInputStream(new File("src/main/resources/config.properties")));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getProperty(String propertyKey) {
        return properties.getProperty(propertyKey);
    }

}
