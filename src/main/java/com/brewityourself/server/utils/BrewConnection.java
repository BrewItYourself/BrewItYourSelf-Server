package com.brewityourself.server.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by sjung on 12/12/15.
 */
public class BrewConnection {
    private Connection jdbcConnection;

    //TODO: Move to config folder
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DATABASE_PROPERTIES = "src/main/resources/database.properties";
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/brewDB";
    private static final String JDBC_USERNAME = "root";
    private static final String JDBC_PASSWORD = "%Jsh381728";

    public BrewConnection() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(DATABASE_PROPERTIES));
            Class.forName(JDBC_DRIVER);
            jdbcConnection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }

}
