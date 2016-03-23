package com.brewityourself.server.service;

import com.brewityourself.server.dto.TempSensor;

import java.util.HashMap;
import java.util.List;

/**
 * Created by sjung on 18/03/16.
 */
public interface TemperatureSensorService {

    void storeTemperature(TempSensor tempSensor);

    void startRecording();

    HashMap<Integer, List<TempSensor>> fetchData();
}
