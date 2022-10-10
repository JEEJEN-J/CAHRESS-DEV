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
public class VisitService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createVisit(String visit) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/visit", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, visit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> findByUuid(String uuid) {
        List<?> user = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/visit/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<?> findActiveVisit(String patientUUID) {
        List<?> user = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/visit?includeInactive=false&v=default&limit=1&patient=" + patientUUID, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<?> findAllVisitsByPatientUUID(String patientUUID) {
        List<?> user = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/visit?includeInactive=true&v=default&limit=3&patient=" + patientUUID, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<?> closeVisit(String visitUUID, String obj) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/visit/" + visitUUID, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, obj);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
