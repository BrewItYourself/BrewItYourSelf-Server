package com.brewityourself.server.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by sjung on 11/02/16.
 */
public class Constants {
    public static final String SERVER_URL = "http://localhost:8080/";

    public static final String DATABASE_PROPERTIES = "database.properties";
    public static final String JDBC_URL = "jdbc.url";
    public static final String JDBC_USERNAME = "jdbc.username";
    public static final String JDBC_PASSWORD = "jdbc.password";

    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();

        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));

            String line = "";
            while ((line = reader.readLine())!= null) {
                output.append(line + "\n");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return output.toString();

    }

}
