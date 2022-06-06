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
public class EncounterService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createEncounter(String encounter) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, encounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> createEncounterProvider(String encounterProvider, String encounterUUID) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter/" + encounterUUID + "/encounterprovider", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, encounterProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> getEncounterByUuid(String uuid) {
        List<?> encounter = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            encounter = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encounter;
    }


    public List<?> getEncounterByPatient(String patientUUID, String encounterUUID) {
        List<?> encounter = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter?patient=" + patientUUID + "&encounterType=" + encounterUUID + "&all=true", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            encounter = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encounter;
    }


    public List<?> getEncounterProviders(String ecounter_uuid) {
        List<?> encounterProviders = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter/" + ecounter_uuid + "/encounterprovider", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            encounterProviders = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encounterProviders;
    }


    public List<?> getEncounterProvidersUuid(String ecounter_uuid, String encounter_provider_uuid) {
        List<?> encounterProviders = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter/" + ecounter_uuid + "/encounterprovider/" + encounter_provider_uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            encounterProviders = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return encounterProviders;
    }

    public List<?> updateEncounter(String encounter, String uuid) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/encounter/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, encounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
