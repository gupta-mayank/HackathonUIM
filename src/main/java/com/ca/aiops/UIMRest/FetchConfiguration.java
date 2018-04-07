package com.ca.aiops.UIMRest;

import com.ca.aiops.Commons;


import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.UriBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

/**
 * This class will be used to fetch the configuration by calling REST API
 */
public class FetchConfiguration {

    public static String getDataEngineThreadCount() {
    try {

        URL url = new URL("http://10.238.20.89:8080/rest/probe/gupma03-2k12-2_domain/gupma03-2k12-2_hub/gupma03-2k12-2/data_engine/config/setup/thread_count_insert");
        byte[] message = ("administrator"+":"+"interOP@123").getBytes("UTF-8");
        String encoded = javax.xml.bind.DatatypeConverter.printBase64Binary(message);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestProperty("Authorization", "Basic "+encoded);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + conn.getResponseCode());
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(
                (conn.getInputStream())));

        String output;
        String output1 = "";
        System.out.println("Output from Server .... \n");
        while ((output = br.readLine()) != null) {
            output1 = output;
            System.out.println(output);

        }
        conn.disconnect();
        return output1;

    } catch (MalformedURLException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
        return null;
    }
}
