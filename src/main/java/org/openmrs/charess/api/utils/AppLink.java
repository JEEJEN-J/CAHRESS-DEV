package org.openmrs.charess.api.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class AppLink {

//    public static final String API_URI = "http://dme.charess.org:8082/openmrs/ws/rest/v1";
//    public static final String API_URI = "http://localhost:8080/openmrs/ws/rest/v1";
    public static final String API_URI = "http://192.168.1.126:8080/openmrs/ws/rest/v1";


    @Autowired
    private DemoProperties demoProperties;

    public void printDemoProperties() {
        System.out.println("API : " + demoProperties.getApi());
    }

    public AppLink() {
    }
}
