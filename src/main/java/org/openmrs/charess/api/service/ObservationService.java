package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.utils.AppLink;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ObservationService {

    private String baseLink = AppLink.API_URI;

    public List<?> createObs(String obs) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/obs", "POST");
            objects = Http.postObject(httpURLConnection, obs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> getObsByUuid(String uuid) {
        List<?> obs = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/obs/" + uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/obs?patient=" + patient_uuid + "&limit=" + limit, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/obs/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, obs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
