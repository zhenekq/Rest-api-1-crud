package com.epam.msu.util;

import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public final class DatabaseProperties {

    private DatabaseProperties() {
    }

    private static final String PATH_TO_PROPERTIES = "D:/EPAM_LABORATORY/RestAPI_1_Backend/src/main/resources/application.properties";
    private static final String vmUsername = "name";
    private static final String vmPassword = "password";

    public static String dbUsername;
    public static String dbPassword;

    static {
        Properties properties = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            properties.setProperty("db.username", getPropertyFromVMOptions(vmUsername));
            properties.setProperty("db.password", getPropertyFromVMOptions(vmPassword));

            dbUsername = properties.getProperty("db.username");
            dbPassword = properties.getProperty("db.password");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private static String getPropertyFromVMOptions(String property) {
        return System.getProperty(property);
    }

}