package com.brewityourself.server.container;

import com.brewityourself.server.utils.GCMSender;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by sjung on 09/02/16.
 */
@Path("gcm_notification")
public class GCMNotificationResource {

    GCMSender gcmSender;

    public GCMNotificationResource() {
        gcmSender = new GCMSender();
    }

    @POST
    @Path("/send")
    @Consumes("application/json")
    public Response gcmSendMessage(String message) {
        System.out.println("gcmSendMessage: " + message);
        GCMSender.gcmSendMessage(message);

        return Response.ok().build();
    }

    @PUT
    @Path("/token")
    @Consumes("application/json")
    public Response recordDeviceToken(String token) {
        System.out.println();

        if (GCMSender.recordDeviceToken(token)) {
            return Response.ok().build();
        } else {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
