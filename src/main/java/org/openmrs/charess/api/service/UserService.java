package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Authenticate;
import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.configuration.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;


@Component
public class UserService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public static String session = null;

    public List<?> getAllUsers() {
        List<?> users = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/user", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            users = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }


    public List<?> logout() throws IOException {
        HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/session", "DELETE");
        try {
            httpURLConnection.setRequestProperty("Authorization", "Basic " + session);
            if (httpURLConnection.getResponseCode() == 204) {
                session = null;
                return Collections.singletonList("Successffully");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Collections.singletonList(httpURLConnection.getResponseCode());
    }


    public List<?> authenticate(String username, String password) {
        List<?> user = new ArrayList<>();
        try {
            session = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/session", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + session);
            System.out.println("Basic : " + session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    public List<?> findByUuid(String uuid) {
        List<?> user = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/user/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
