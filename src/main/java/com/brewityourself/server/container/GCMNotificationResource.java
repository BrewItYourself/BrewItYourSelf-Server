package com.brewityourself.server.container;

import com.brewityourself.server.utils.GCMSender;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
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
    public Response gcmSendMessage(String message) {

        GCMSender.gcmSendMessage(message);

        return Response.ok().build();
    }
}
