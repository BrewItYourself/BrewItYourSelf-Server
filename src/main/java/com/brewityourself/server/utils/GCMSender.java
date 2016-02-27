package com.brewityourself.server.utils;

/**
 * Created by sjung on 12/12/15.
 */

import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class GCMSender {
    private static final String API_KEY = "AIzaSyDljkGVcZffSovz-jxi7Sl3nu8kQZOcZFQ";
    private static final String GCM_URL = "https://android.googleapis.com/gcm/send";

    public static void gcmSendMessage(String message) {
        gcmSendMessage(null, message);
    }

    public static void gcmSendMessage(String to, String message) {
        try {
            // Prepare JSON containing the GCM message content. What to send and where to send.
            JSONObject jGcmData = new JSONObject();
            JSONObject jData = new JSONObject();
            jData.put("message", message);
            // Where to send GCM message.
            if (to != null) {
                jGcmData.put("to", to.trim());
            } else {
                jGcmData.put("to", "/topics/global");
            }
            // What to send in GCM message.
            jGcmData.put("data", jData);

            // Create connection to send GCM Message request.
            URL url = new URL(GCM_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "key=" + API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            // Send GCM message content.
            OutputStream outputStream = conn.getOutputStream();
            outputStream.write(jGcmData.toString().getBytes());

            // Read GCM response.
            InputStream inputStream = conn.getInputStream();
            String resp = IOUtils.toString(inputStream);
            System.out.println(resp);
            System.out.println("Sent to Device");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}