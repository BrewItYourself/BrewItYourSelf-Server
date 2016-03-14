package com.brewityourself.server.utils;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Created by sjung on 12/12/15.
 */
public class BrewDatabaseConnection {
    private Connection jdbcConnection;

    public BrewDatabaseConnection() {
        try {
            Configuration config = new PropertiesConfiguration(Constants.DATABASE_PROPERTIES);
            String jdbcUrl = config.getString(Constants.JDBC_URL);
            String jdbcUsername = config.getString(Constants.JDBC_USERNAME);
            String jdbcPassword = config.getString(Constants.JDBC_PASSWORD);

            jdbcConnection = DriverManager.getConnection(jdbcUrl, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }
    }

    public Connection getJdbcConnection() {
        return jdbcConnection;
    }

}
