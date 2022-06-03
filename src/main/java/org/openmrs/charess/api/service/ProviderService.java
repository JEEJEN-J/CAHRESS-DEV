package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProviderService {

    @Autowired
    private ApplicationProperties applicationProperties;
    private InputStreamReader inputStreamReader;
    private String output;

    public List<?> getProviderByUser(String user) {
        List<?> provider = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/provider/"+user, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            provider = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return provider;
    }

    public List<?> allProviders() {
        List<?> providers = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/provider", "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            providers = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return providers;
    }
}
