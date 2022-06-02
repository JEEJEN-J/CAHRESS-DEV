package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.utils.AppLink;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.List;

@Component
public class VisitService {

    private String baseLink = AppLink.API_URI;

    public List<?> createVisit(String visit) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/visit", "POST");
            objects = Http.postObject(httpURLConnection, visit);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
