package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Component
public class LocationService {

    @Autowired
    private ApplicationProperties applicationProperties;
    private InputStreamReader inputStreamReader;
    private String output;

    public List<Object> getAllLocations() {
        List<Object> allLocations = new ArrayList<>();
        try {
            URL url = new URL(applicationProperties.getBaseUrl() + "/location");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200)
                throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());

            inputStreamReader = new InputStreamReader(conn.getInputStream());
            BufferedReader br = new BufferedReader(inputStreamReader);
            while ((output = br.readLine()) != null)
                allLocations.add(output);
            conn.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return allLocations;
    }
}
