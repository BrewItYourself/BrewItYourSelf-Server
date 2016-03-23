package com.brewityourself.server.service.impl;

import com.brewityourself.server.dto.TempSensor;
import com.brewityourself.server.service.TemperatureSensorService;
import com.brewityourself.server.utils.BrewDatabaseConnection;
import com.brewityourself.server.utils.Constants;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sjung on 18/03/16.
 */
public class TemperatureSensorServiceImpl implements TemperatureSensorService {
    private BrewDatabaseConnection brewDatabaseConnection;

    public TemperatureSensorServiceImpl() {
        brewDatabaseConnection = new BrewDatabaseConnection();
    }
    @Override
    public void storeTemperature(TempSensor tempSensor) {
        String sqlInsert = "INSERT INTO temperatureSensorData(id,temperature) VALUES(?, ?) ";
        try {
            PreparedStatement preparedStatement = brewDatabaseConnection
                    .getJdbcConnection()
                    .prepareStatement(sqlInsert);

            preparedStatement.setInt(1, tempSensor.getId());
            preparedStatement.setDouble(2, tempSensor.getTemperature());

            preparedStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startRecording() {
        String command = "rosrun rest_node subscribe";
        //TODO: Change to actual command when implemented
        System.out.println("Running Command: " + command);
        Constants.executeCommand(command);
    }

    public void dropData() {
        String sql = "TRUNCATE TABLE temperatureSensorData";
        try {
            PreparedStatement preparedStatement = brewDatabaseConnection
                    .getJdbcConnection()
                    .prepareStatement(sql);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    public HashMap<Integer, List<TempSensor>> fetchData() {
        String sqlInsert = "SELECT id, temperature, time_stamp FROM temperatureSensorData ORDER BY id, time_stamp ";
        HashMap<Integer, List<TempSensor>> tempSensorsMap = new HashMap<>();

        try {
            PreparedStatement preparedStatement = brewDatabaseConnection
                    .getJdbcConnection()
                    .prepareStatement(sqlInsert);

            ResultSet resultSet = preparedStatement.executeQuery();
            int prev_id = 0;
            List<TempSensor> tempSensorList = new ArrayList<>();

            while (resultSet.next()) {

                System.out.println("id:"+resultSet.getInt("id") + " temperature:"
                        + resultSet.getDouble("temperature") + " time stamp:"
                        +resultSet.getTimestamp("time_stamp"));
                TempSensor tempSensor = new TempSensor();
                tempSensor.setId(resultSet.getInt("id"));

                if (tempSensor.getId() != prev_id) {
                    tempSensorsMap.put(prev_id, tempSensorList);
                    tempSensorList = new ArrayList<>();
                    prev_id = tempSensor.getId();
                }

                tempSensor.setTemperature(resultSet.getDouble("temperature"));
                tempSensor.setTimestamp(resultSet.getTimestamp("time_stamp"));
                tempSensorList.add(tempSensor);
            }
            tempSensorsMap.put(prev_id, tempSensorList);

            dropData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempSensorsMap;
    }
}
