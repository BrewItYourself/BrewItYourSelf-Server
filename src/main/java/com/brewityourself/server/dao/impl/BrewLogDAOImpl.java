package com.brewityourself.server.dao.impl;

import com.brewityourself.server.dao.BrewLogDAO;
import com.brewityourself.server.dto.BrewLog;
import com.brewityourself.server.utils.BrewConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sjung on 13/12/15.
 */
public class BrewLogDAOImpl implements BrewLogDAO {

    private BrewConnection brewConnection;

    private static final String BREW_ID = "brewid";
    private static final String BREW_STATE = "brewstate";
    private static final String BREW_TEMPERATURE = "temperature";
    private static final String BREW_LOGTIME = "logtime";

    public BrewLogDAOImpl() {
        brewConnection = new BrewConnection();
    }

    public List<BrewLog> getBrewLogs(int brewid) {
        String sqlStatement = "SELECT * FROM brewlogs where brewid = ?";
        try {
            PreparedStatement preparedStatement = brewConnection.getJdbcConnection().prepareStatement(sqlStatement);
            preparedStatement.setInt(1, brewid);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<BrewLog> brewLogs = new ArrayList<>();
            while (resultSet.next()) {
                BrewLog brewLog = new BrewLog();
                brewLog.setBrewId(resultSet.getInt(BREW_ID));
                brewLog.setBrewState(resultSet.getString(BREW_STATE));
                brewLog.setTemperature(resultSet.getDouble(BREW_TEMPERATURE));
                brewLog.setLogTime(resultSet.getDate(BREW_LOGTIME));

                brewLogs.add(brewLog);
            }
            return brewLogs;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean putBrewLog(BrewLog brewLog) {
        String insertString = "INSERT INTO brewlogs (brewid, logtime, brewstate, temperature) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement preparedStatement = brewConnection.getJdbcConnection().prepareStatement(insertString);
            preparedStatement.setInt(1, brewLog.getBrewId());
            preparedStatement.setDate(2, brewLog.getLogTime());
            preparedStatement.setString(3, brewLog.getBrewState());
            preparedStatement.setDouble(4, brewLog.getTemperature());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public List<String> getBrews() {
        String selectString = "SELECT DISTINCT brewid FROM brewlogs";

        return null;
    }

    @Override
    public BrewLog getCurrentBrewLog(int brewid) {
        String sqlStatement = "SELECT * FROM brewlogs where brewid = ?";
        try {
            PreparedStatement preparedStatement = brewConnection.getJdbcConnection().prepareStatement(sqlStatement);
            ResultSet resultSet = preparedStatement.executeQuery();

            BrewLog brewLog = new BrewLog();
            brewLog.setBrewId(resultSet.getInt(BREW_ID));
            brewLog.setBrewState(resultSet.getString(BREW_STATE));
            brewLog.setTemperature(resultSet.getDouble(BREW_TEMPERATURE));
            brewLog.setLogTime(resultSet.getDate(BREW_LOGTIME));

            return brewLog;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public BrewLog testBrewLog() {
        BrewLog brewLog = new BrewLog();
        brewLog.setBrewId(1);
        brewLog.setBrewState("TEST");
        brewLog.setTemperature(22.1);

        return brewLog;
    }
}
