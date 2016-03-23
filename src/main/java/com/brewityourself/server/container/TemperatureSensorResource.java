package com.brewityourself.server.container;

import com.brewityourself.server.dto.TempSensor;
import com.brewityourself.server.service.TemperatureSensorService;
import com.brewityourself.server.service.impl.TemperatureSensorServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;

/**
 * Created by sjung on 18/03/16.
 */
@Path("/temp_sensor")
public class TemperatureSensorResource {

    private TemperatureSensorService temperatureSensorService;

    public TemperatureSensorResource() {
        temperatureSensorService = new TemperatureSensorServiceImpl();
    }

    @GET
    @Path("/fetch")
    @Produces("application/json")
    public Response fetchData() {
        HashMap<Integer, List<TempSensor>> temperatures =  temperatureSensorService.fetchData();

        return Response.ok().entity(temperatures).build();
    }

    @PUT
    @Path("/start")
    @Consumes("application/json")
    public Response startRecording() {

        temperatureSensorService.startRecording();
        return Response.ok().build();
    }

    @PUT
    @Path("/store")
    @Consumes("application/json")
    public Response storeTemperature(TempSensor tempSensor) {
        temperatureSensorService.storeTemperature(tempSensor);

        return Response.ok().build();
    }
}
