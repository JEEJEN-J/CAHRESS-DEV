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
public class ConceptService {

    @Autowired
    private ApplicationProperties applicationProperties;

    public List<?> createConcept(String concept) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/concept", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, concept);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptSource(String conceptSource) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptsource/", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptSource);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptAttributeType(String conceptAttributeType) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptattributetype", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptAttributeType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptMapType(String conceptmaptype) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptmaptype", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptmaptype);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptReferenceTerm(String conceptreferenceterm) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptreferenceterm", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptreferenceterm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptClass(String conceptclass) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptclass/", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptProposal(String conceptproposal) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptproposal", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptproposal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }

    public List<?> createConceptStopWord(String conceptstopword) {
        List<?> objects = null;
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptstopword", "POST");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            objects = Http.postObject(httpURLConnection, conceptstopword);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objects;
    }



    /*
     * Find concept by uuid
     * */

    public List<?> getConceptAttributeType(String uuid) {
        List<?> conceptattributetype = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptattributetype/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptattributetype = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptattributetype;
    }

    public List<?> getConceptDataType(String uuid) {
        List<?> conceptdatatypes = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptdatatype/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptdatatypes = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptdatatypes;
    }

    public List<?> getConceptMapType(String uuid) {
        List<?> conceptmaptypes = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptmaptype/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptmaptypes = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptmaptypes;
    }

    public List<?> getConceptReferenceTerm(String uuid) {
        List<?> conceptreferenceterms = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptreferenceterm/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptreferenceterms = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptreferenceterms;
    }

    public List<?> findConceptReferenceTerm(String codeOrName) {
        List<?> conceptreferenceterms = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptreferenceterm?codeOrName=" + codeOrName + "/", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptreferenceterms = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptreferenceterms;
    }

    public List<?> getConceptClass(String uuid) {
        List<?> conceptclass = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptclass/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptclass = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptclass;
    }

    public List<?> getConceptProposal(String uuid) {
        List<?> conceptproposal = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptproposal/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptproposal = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptproposal;
    }

    public List<?> getConceptStopWord(String uuid) {
        List<?> conceptstopword = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptstopword/" + uuid, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptstopword = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptstopword;
    }



    /*
     * List all concept
     */

    public List<?> listConceptClass(Integer limit) {
        List<?> conceptclass = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptclass?limit=" + limit, "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptclass = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptclass;
    }

    public List<?> listConceptProposal() {
        List<?> conceptproposals = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptproposa", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptproposals = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptproposals;
    }

    public List<?> listConceptStopWord() {
        List<?> conceptstopwords = new ArrayList<>();
        try {
            HttpURLConnection httpURLConnection = Http.getHttpConnection(applicationProperties.getBaseUrl() + "/conceptstopword", "GET");
            httpURLConnection.setRequestProperty("Authorization", "Basic " + UserService.session);
            if (httpURLConnection.getResponseCode() != 200)
                return Collections.singletonList(httpURLConnection.getResponseCode());
            conceptstopwords = Http.getObject(httpURLConnection);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conceptstopwords;
    }

}
