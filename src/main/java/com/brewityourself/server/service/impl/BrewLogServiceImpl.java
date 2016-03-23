package com.brewityourself.server.service.impl;

import com.brewityourself.server.dao.BrewLogDAO;
import com.brewityourself.server.dao.impl.BrewLogDAOImpl;
import com.brewityourself.server.dto.BrewLog;
import com.brewityourself.server.dto.BrewRecipe;
import com.brewityourself.server.service.BrewLogService;
import com.brewityourself.server.utils.BrewState;
import com.brewityourself.server.utils.Constants;
import com.brewityourself.server.utils.GCMSender;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.List;

/**
 * Created by sjung on 21/01/16.
 */
public class BrewLogServiceImpl implements BrewLogService {

    private BrewLogDAO brewLogDAO;

    public BrewLogServiceImpl() {
        brewLogDAO = new BrewLogDAOImpl();
    }

    @Override
    public List<BrewLog> getBrewLogs(int brewid) {
        return brewLogDAO.getBrewLogs(brewid);
    }

    @Override
    public List<String> getBrews() {
        return brewLogDAO.getBrews();
    }

    @Override
    public boolean putBrewLog(BrewLog brewLog) {
        return brewLogDAO.putBrewLog(brewLog);
    }

    @Override
    public boolean sendBrewData(int brewid) {
        brewLogDAO.getCurrentBrewLog(brewid);
        return false;
    }

    @Override
    public BrewLog testBrewLog() {
        return brewLogDAO.testBrewLog();
    }

    @Override
    public void startBrew(BrewRecipe brewRecipe) {
        String command = "rostopic pub heater std_msgs/Bool true --once";
        //TODO: CALL Whatever starts the brew and hopefully return the date
        System.out.println("Running Command: " + command);
        Constants.executeCommand(command);

        BrewLog brewLog = new BrewLog();
        brewLog.setBrewId(1);
        brewLog.setBrewState(BrewState.STARTED.name());
        brewLog.setLogTime(new Date(new java.util.Date().getTime()));
        brewLog.setTemperature(22);

        brewLogDAO.putBrewLog(brewLog);

    }

    @Override
    public void startHeat(boolean start) {
        String command;
        if (start) {
            command = "rostopic pub heater std_msgs/Bool true --once";
        } else {
            command = "rostopic pub heater std_msgs/Bool false --once";
        }
        System.out.println("Running Command:" +command);

        Constants.executeCommand(command);
    }

}
