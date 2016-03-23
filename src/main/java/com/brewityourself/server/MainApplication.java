package com.brewityourself.server;

import com.brewityourself.server.container.BrewLogResource;
import com.brewityourself.server.container.GCMNotificationResource;
import com.brewityourself.server.container.TemperatureSensorResource;
import com.brewityourself.server.utils.Constants;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;
import java.net.URI;

/**
 * Created by sjung on 11/12/15.
 */
public class MainApplication extends ResourceConfig {
    private static final URI BASE_URI = URI.create(Constants.SERVER_URL);

    public MainApplication() {
        packages("com.brewityourself.server.container");
        register(BrewLogResource.class);
        register(GCMNotificationResource.class);
        register(TemperatureSensorResource.class);
        register(JacksonJsonProvider.class);
        register(JacksonFeature.class);
    }

    public static void main(String[] args) {
        System.out.println("BrewItYourSelf RESTful Web Server");

        MainApplication mainApplication = new MainApplication();
        final HttpServer server = JdkHttpServerFactory.createHttpServer(BASE_URI, mainApplication, false);
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(mainApplication, HttpHandler.class);

        server.createContext(BASE_URI.getPath(), handler);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            server.stop(0);
        }));

        server.start();
    }

}
