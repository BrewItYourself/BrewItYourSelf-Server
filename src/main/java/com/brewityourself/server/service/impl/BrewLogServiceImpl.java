package com.brewityourself.server.service.impl;

import com.brewityourself.server.dao.BrewLogDAO;
import com.brewityourself.server.dao.impl.BrewLogDAOImpl;
import com.brewityourself.server.dto.BrewLog;
import com.brewityourself.server.service.BrewLogService;
import com.brewityourself.server.utils.GCMSender;

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


}
