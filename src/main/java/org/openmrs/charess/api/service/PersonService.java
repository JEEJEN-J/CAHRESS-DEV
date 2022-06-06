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
public class PersonService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createPerson(String person) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPersonName(String uuid, String personName) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/name", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPersonAddress(String uuid, String personAddress) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/address", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPersonAttribute(String uuid, String personAttribute) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/attribute", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createPersonAttributeType(String personattributetype) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/personattributetype", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personattributetype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> getPersonByUuid(String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidName(String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/name", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidAddress(String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/address", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidAttribute(String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid + "/attribute", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidNameUuid(String parent_uuid, String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/name/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidAddressUuid(String parent_uuid, String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/address/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonByParentUuidAttributeUuid(String parent_uuid, String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/attribute/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> getPersonAttributeTypeByUuid(String uuid) {
        List<?> persons = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/personattributetype/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            persons = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }

    public List<?> updatePerson(String uuid, String person) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, person);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePersonName(String parent_uuid, String uuid, String personName) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/name/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePersonAddress(String parent_uuid, String uuid, String personAddress) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/address/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personAddress);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> updatePersonAttribute(String parent_uuid, String uuid, String personAttribute) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/person/" + parent_uuid + "/attribute/" + uuid, "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, personAttribute);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

}
