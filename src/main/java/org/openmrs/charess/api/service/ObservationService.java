package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ObservationService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createObs(String obs) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/obs", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, obs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> getObsByUuid(String uuid) {
        List<?> obs = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/obs/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            obs = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obs;
    }


    public List<?> allObs(String patient_uuid, Integer limit) {
        List<?> obs = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/obs?patient=" + patient_uuid + "&limit=" + limit, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            obs = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obs;
    }


    public List<?> updateObs(String uuid, String obs) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/obs/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, obs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
