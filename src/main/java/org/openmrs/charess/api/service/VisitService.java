package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
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

}
