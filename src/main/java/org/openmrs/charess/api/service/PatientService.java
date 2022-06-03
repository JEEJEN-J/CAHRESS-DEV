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
public class PatientService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createPatient(String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPatientIdentifier(String parent_uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/identifier", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPatientAllergy(String parent_uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/allergy", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> getPatientByCriteria(String criteria, Integer limit) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient?q=" + criteria + "&v=default&limit=" + limit, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByUuid(String uuid) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + uuid, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByIdentifier(String identifier) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient?identifier=" + identifier, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByParentUuidIdentifier(String parent_uuid) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/identifier", "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByParentUuidIdentifierUuid(String parent_uuid, String uuid) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/identifier/" + uuid, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByParentUuidAllergy(String parent_uuid) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/allergy", "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }

    public List<?> getPatientByParentUuidAllergyUuid(String parent_uuid, String uuid) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/allergy/" + uuid, "GET");
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            patients = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return patients;
    }


    public List<?> updatePatient(String parent_uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/identifier", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePatientIdentifier(String parent_uuid, String uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/identifier/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePatientAllergy(String parent_uuid, String uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/patient/" + parent_uuid + "/allergy/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
