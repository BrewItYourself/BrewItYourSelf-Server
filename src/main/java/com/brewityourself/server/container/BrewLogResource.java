package com.brewityourself.server.container;

import com.brewityourself.server.dto.BrewLog;
import com.brewityourself.server.dto.BrewRecipe;
import com.brewityourself.server.service.BrewLogService;
import com.brewityourself.server.service.impl.BrewLogServiceImpl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by sjung on 12/12/15.
 */
@Path("brewlog")
public class BrewLogResource {

    private BrewLogService brewLogService;

    public BrewLogResource() {
        brewLogService = new BrewLogServiceImpl();
    }

    @GET
    @Path("/{brewid}")
    @Produces("application/json")
    public Response getBrewLogs(@PathParam("brewid") int brewid) {
        System.out.println("brewid: " + brewid);
        List<BrewLog> brewLogs = brewLogService.getBrewLogs(brewid);

        if (brewLogs == null) {
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .build();
        } else if (brewLogs.isEmpty()) {
            return Response
                    .ok()
                    .type(MediaType.APPLICATION_JSON_TYPE)
                    .entity(brewLogs)
                    .build();
        } else {
            return Response
                    .noContent()
                    .build();
        }

    }

    @GET
    @Path("/all_brews")
    public List<String> getBrews() {
        return brewLogService.getBrews();
    }

    @PUT
    @Path("brew_data/")
    @Consumes("application/json")
    public Response putBrewData(BrewLog brewLog) {
        brewLogService.putBrewLog(brewLog);

        return Response
                .accepted()
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @POST
    @Path("/{brewid}")
    public Response sendBrewData(@PathParam("brewid") int brewid) {
        if (brewLogService.sendBrewData(brewid)) {
            return Response.ok().build();
        } else {
            return Response.ok().build();
        }
    }

    @GET
    @Path("/test")
    @Produces("application/json")
    public Response testBrewLog() {
        System.out.println("testbrewlog");

        return Response
                .ok()
                .entity(brewLogService.testBrewLog())
                .type(MediaType.APPLICATION_JSON_TYPE)
                .build();
    }

    @PUT
    @Path("/start")
    @Produces("application/json")
    public Response startBrew(BrewRecipe brewRecipe) {

        brewLogService.startBrew(brewRecipe);

        return Response.status(Response.Status.CREATED).build();
    }
}
