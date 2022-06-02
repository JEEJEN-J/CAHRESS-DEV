package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Authenticate;
import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.utils.AppLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collections;
import java.util.List;


@Component
public class UserService {

    private String baseLink = AppLink.API_URI;

    public List<?> getAllUsers() {
        List<?> users = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/user", "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            users = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public List<?> authenticate(String username, String password) {
        AppLink appLink = new AppLink();
        appLink.printDemoProperties();
        List<?> user = new ArrayList<>();
        try {
            String encoded = Base64.getEncoder().encodeToString((username + ":" + password).getBytes(StandardCharsets.UTF_8));
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/user?username=" + username, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + encoded);

            new Authenticate(username, password).start();
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/user/" + uuid, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            user = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
