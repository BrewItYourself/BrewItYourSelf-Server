package com.brewityourself.server.dao;

import com.brewityourself.server.dto.BrewLog;

import java.util.List;

/**
 * Created by sjung on 21/01/16.
 */
public interface BrewLogDAO {

    /**
     * Gets brew logs from database for corresponding brewid
     * @param brewid
     * @return list of brewlogs
     */
    List<BrewLog> getBrewLogs(int brewid);

    /**
     * Put BrewLogs into database
     * @param brewLog
     * @return true/false whether successful or not
     */
    boolean putBrewLog(BrewLog brewLog);

    /**
     * Gets Brew title for all existing brews
     * @return list of brews
     */
    List<String> getBrews();

    /**
     * Gets the most recent brewdata
     * @param brewid
     * @return BrewLog
     */
    BrewLog getCurrentBrewLog(int brewid);

    /**
     * Get a test Brew log
     * @return a Brewlog
     */
    BrewLog testBrewLog();
}
