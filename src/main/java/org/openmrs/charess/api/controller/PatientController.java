package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openmrs.charess.api.service.PatientService;
import org.openmrs.charess.api.service.EncounterService;
import org.openmrs.charess.api.utils.GeneratedIdentifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/openmrs/ws/rest/v1/patient")
@CrossOrigin
public class PatientController {

    public PatientService patientService;
    public EncounterService encounterService;


    @Autowired
    public PatientController(PatientService patientService, EncounterService encounterService) {
        this.patientService = patientService;
        this.encounterService = encounterService;
    }


    @Operation(summary = "Créer un patient, vous devez spécifier les propriétés ci-dessous dans la demande. Si vous n'êtes pas connecté pour effectuer cette action, " +
            "un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createPatient(@RequestBody String responseBody) throws ParseException {

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        String identifierTypeUuid = "05a29f94-c0ed-11e2-94be-8c13b969e334";

        StringBuilder builder = new StringBuilder();

        GeneratedIdentifier generatedIdentifier = new GeneratedIdentifier();
        String identifier = "100" + generatedIdentifier.identifierToDigits();
        String digits = "";

        do {
            digits = generatedIdentifier.getValidIdentifier(identifier);
        } while (patientService.getPatientByIdentifier(digits).size() != 1);

        JSONObject jsonObject;
        JSONObject patientJSON;
        JSONParser parser = new JSONParser();

        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);

            StringBuilder patientBuilder = new StringBuilder(jsonObject.get("patient").toString().substring(1, jsonObject.get("patient").toString().length() - 1));
            patientJSON = new JSONObject(parser.parse(patientBuilder.toString()).toString());


            String identifiers = patientJSON.get("identifiers").toString().substring(1, patientJSON.get("identifiers").toString().length() - 1);
            JSONObject jsonIdent = new JSONObject(parser.parse(identifiers).toString());

            String location = jsonIdent.get("location").toString();

            builder.append("{\n" +
                    "         \"identifier\":\"" + digits + "\",\n" +
                    "         \"identifierType\":\"" + identifierTypeUuid + "\",\n" +
                    "         \"location\":\"" + location + "\",\n" +
                    "      }");

            JSONObject json = new JSONObject(builder.toString());
            patientJSON.append("identifiers", json);
            jsonObject.remove("patient");
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }

        Object object = patientService.createPatient(patientJSON.toString());

        StringBuilder patientsBuilders = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONObject objson = new JSONObject(parser.parse(patientsBuilders.toString()).toString());
        String buiderUUID = objson.get("uuid").toString();
        jsonObject.put("patient", buiderUUID);

        JSONArray jsonArrayObs = jsonObject.getJSONArray("obs");
        jsonObject.remove("obs");


        for (int i = 0; i < jsonArrayObs.length(); i++) {
            if ((jsonArrayObs.getJSONObject(i).names().optString(0)).equalsIgnoreCase("groupMembers")) {
                int count = jsonArrayObs.getJSONObject(i).getJSONArray("groupMembers").length();
                for (int k = 0; k < count; k++) {
                    jsonArrayObs.getJSONObject(i).getJSONArray("groupMembers").getJSONObject(k).put("person", buiderUUID);
                }
            }
            jsonArrayObs.getJSONObject(i).put("person", buiderUUID);
        }

        jsonObject.put("obs", jsonArrayObs);
        System.out.println("jsonObject : "+jsonObject);

        Object encounterData = encounterService.createEncounter(jsonObject.toString());
        StringBuilder encounterBuild = new StringBuilder(encounterData.toString().substring(1, encounterData.toString().length() - 1));

        return ResponseEntity.ok(encounterBuild.toString());
    }


    @Operation(summary = "Créer une sous-ressource patientIdentifier pour une ressource patient spécifique, vous devez spécifier les propriétés ci-dessous dans le corps" +
            " de votre requête. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/identifier", method = RequestMethod.POST)
    public ResponseEntity<?> createPatientIdentifier(@PathVariable String parent_uuid,
                                                     @RequestBody String responseBody) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());

            Object object = patientService.createPatientIdentifier(parent_uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(jsonObject.toString());

    }


    @Operation(summary = "Créer une sous-ressource d'allergie pour une ressource patient spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre" +
            " requête. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/allergy", method = RequestMethod.POST)
    public ResponseEntity<?> createPatientAllergy(@PathVariable("parent-uuid") String parent_uuid,
                                                  @RequestBody String responseBody) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("ALLERGY TO SAVE : " + jsonObject);
            Object object = patientService.createPatientAllergy(parent_uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
            System.out.println("ALLERGY SAVED : " + jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer un patient par son UUID. Renvoie un 404 Not Foundstatut si le patient n'existe pas dans le système. Si l'utilisateur n'est pas connecté" +
            " pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{criteria}/{limit}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientByCriteria(@PathVariable("criteria") String criteria,
                                                  @PathVariable("limit") Integer limit) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByCriteria(criteria, limit);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer un patient par son UUID. Renvoie un 404 Not Foundstatut si le patient n'existe pas dans le système. Si l'utilisateur n'est pas connecté" +
            " pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientUuid(@PathVariable("uuid") String uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByUuid(uuid);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupère toutes les sous-ressources d' identifiant d'une ressource patient par . target_patient_uuid Renvoie un 404 Not Found statut si " +
            "patientIdentifier n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/identifier", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientParentUuidIdentifier(@PathVariable("parent-uuid") String parent_uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByParentUuidIdentifier(parent_uuid);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer une sous-ressource patientIdentifier d'une ressource patient . Renvoie un 404 Not Found statut si patientIdentifier n'existe pas. " +
            "Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/identifier/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientParentUuidIdentifierUuid(@PathVariable("parent-uuid") String parent_uuid,
                                                                @PathVariable("uuid") String uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByParentUuidIdentifierUuid(parent_uuid, uuid);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }

    @Operation(summary = "Récupérer une sous-ressource allergie d'une ressource patient . Renvoie un 404 Not Found statut si l'allergie n'existe pas pour ce patient." +
            " Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/allergy", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientParentUuidAllergy(@PathVariable("parent-uuid") String parent_uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByParentUuidAllergy(parent_uuid);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer une sous-ressource allergie d'une ressource patient . Renvoie un 404 Not Found statut si l'allergie n'existe pas. " +
            "Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/allergy/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getPatientParentUuidAllergyUuid(@PathVariable("parent-uuid") String parent_uuid, @PathVariable("uuid") String uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = patientService.getPatientByParentUuidAllergyUuid(parent_uuid, uuid);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Par exemple, nous voulons simplement ajouter un identifiant supplémentaire. Mettre à jour un patient cible avec un UUID donné, " +
            "cette méthode ne modifie que les propriétés de la requête. ")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatient(@PathVariable String uuid,
                                           @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        Object object;
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            object = patientService.updatePatient(uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Met à jour une valeur de sous-ressource patientIdentifier avec un UUID donné, cette méthode ne modifiera que la valeur de la sous-ressource." +
            " Renvoie un 404 Not Foundstatut si l'attribut n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, " +
            "un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/identifier/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatientIdentifier(@PathVariable("parent-uuid") String parent_uuid,
                                                     @PathVariable("uuid") String uuid,
                                                     @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        Object object;
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            object = patientService.updatePatientIdentifier(parent_uuid, uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Met à jour une valeur de sous-ressource d'allergie avec un UUID donné, cette méthode ne modifiera que la valeur de la sous-ressource." +
            " Renvoie un 404 Not Foundstatut si la propriété n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action," +
            " un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/allergy/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePatientAllergy(@PathVariable("parent-uuid") String parent_uuid,
                                                  @PathVariable("uuid") String uuid,
                                                  @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("ALLERGY TO UPDATE : " + jsonObject);
            Object object = patientService.updatePatientAllergy(parent_uuid, uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
            System.out.println("ALLERGY UPDATED : " + jsonObject);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(jsonObject.toString());
    }

}
