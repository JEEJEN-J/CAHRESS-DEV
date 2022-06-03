package org.openmrs.charess.api.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ApplicationProperties {

    @Value("${configuration.protocol}")
    public String protocole;

    @Value("${configuration.host}")
    public String host;

    @Value("${configuration.port}")
    public Integer port;

    @Value("${configuration.api}")
    public String api;

    public ApplicationProperties() {
    }

    public String getBaseUrl() {
        return protocole + "://" + host + ":" + port + "/" + api;
    }
}
