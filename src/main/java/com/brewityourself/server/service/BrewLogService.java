package com.brewityourself.server.service;

import com.brewityourself.server.dto.BrewLog;

import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sjung on 21/01/16.
 */
public interface BrewLogService {

    /**
     * Get BrewLogs based on the brewid specified
     * @param brewid value of the brew
     * @return List of BrewLogs
     */
    List<BrewLog> getBrewLogs(int brewid);

    /**
     * Gets Brew title for all existing brews
     * @return list of brews
     */
    List<String> getBrews();

    /**
     * Puts a BrewLog into the database
     * @param brewLog
     * @return true/false whether
     */
    boolean putBrewLog(BrewLog brewLog);

    /**
     * Call to the server to send BrewData to Android Device
     * @param brewid
     * @return
     */
    boolean sendBrewData(int brewid);

    BrewLog testBrewLog();
}
