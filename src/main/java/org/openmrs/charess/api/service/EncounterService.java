package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.utils.AppLink;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class EncounterService {

    private String baseLink = AppLink.API_URI;

    public List<?> createEncounter(String encounter) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter", "POST");
            objects = Http.postObject(httpURLConnection, encounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> createEncounterProvider(String encounterProvider, String encounterUUID) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter/" + encounterUUID + "/encounterprovider", "POST");
            objects = Http.postObject(httpURLConnection, encounterProvider);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }


    public List<?> getEncounterByUuid(String uuid) {
        List<?> encounter = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter/" + uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter?patient=" + patientUUID + "&encounter_Type=" + encounterUUID + "&all=true", "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter/" + ecounter_uuid + "/encounterprovider", "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter/" + ecounter_uuid + "/encounterprovider/" + encounter_provider_uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/encounter/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, encounter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
