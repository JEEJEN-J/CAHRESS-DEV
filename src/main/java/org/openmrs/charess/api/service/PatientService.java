package org.openmrs.charess.api.service;

import org.openmrs.charess.api.configuration.Http;
import org.openmrs.charess.api.utils.AppLink;
import org.openmrs.charess.api.utils.GeneratedIdentifier;
import org.springframework.stereotype.Component;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class PatientService {

    private String baseLink = AppLink.API_URI;

    public List<?> createPatient(String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPatientIdentifier(String parent_uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/identifier", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPatientAllergy(String parent_uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/allergy", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> getPatientByCriteria(String criteria, Integer limit) {
        List<?> patients = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient?q=" + criteria + "&v=default&limit=" + limit, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient?identifier=" + identifier, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/identifier", "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/identifier/" + uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/allergy", "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/allergy/" + uuid, "GET");
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
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/identifier", "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePatientIdentifier(String parent_uuid, String uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/identifier/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePatientAllergy(String parent_uuid, String uuid, String patient) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(baseLink + "/patient/" + parent_uuid + "/allergy/" + uuid, "POST");
            objects = Http.postObject(httpURLConnection, patient);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
