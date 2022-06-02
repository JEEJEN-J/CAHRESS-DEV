package org.openmrs.charess.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openmrs.charess.api.service.EncounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/encounter")
@CrossOrigin
public class EncounterController {

    public EncounterService encounterService;

    @Autowired
    public EncounterController(EncounterService encounterService) {
        this.encounterService = encounterService;
    }


    @Operation(summary = "Créer une nouvelle Rencontre pour une nouvelle Visite, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si vous n'êtes pas" +
            " connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createEncounter(@RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("OBS : " + jsonObject);

            Object object = encounterService.createEncounter(jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        System.out.println("Encounter : " + jsonObject);
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Créer une nouvelle Rencontre pour une nouvelle Visite, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si vous n'êtes pas" +
            " connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/obs", method = RequestMethod.POST)
    public ResponseEntity<?> createEncounterObs(@RequestBody String responseBody) {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("ECOUNTER : " + jsonObject);

            JSONArray obs = jsonObject.getJSONArray("obs");
            System.out.println("OBS : "+obs);

//            JSONArray groupesMembers = obs.getJSONObject(1).getJSONArray().

            Object object = encounterService.createEncounter(jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        System.out.println("Vitals : " + jsonObject);
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Pour créer une sous-ressource d'attribut pour une ressource de visite spécifique, vous devez spécifier les attributs ci-dessous dans le corps de la" +
            " requête. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/encounterProvider/{uuid}", method = RequestMethod.POST)
    public ResponseEntity<?> createEncounterProvider(@PathVariable String encounter_uuid,
                                                     @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(encounterService.createEncounter(jsonObject.toString()));
    }


    @Operation(summary = "Récupérer une rencontre par son UUID. Renvoie un 404 Not Found statut si Rencontre n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer " +
            "cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/encounter/{uuid}", method = RequestMethod.GET)
    public List<?> getEncounter(@PathVariable("uuid") String uuid) {
        return encounterService.getEncounterByUuid(uuid);
    }


    @RequestMapping(value = "/{encounter-uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getEncounterByUuid(@PathVariable("encounter-uuid") String encounterUUID) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = encounterService.getEncounterByUuid(encounterUUID);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @RequestMapping(value = "/{patient-uuid}/{encounter-uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getEncounterByPatient(@PathVariable("patient-uuid") String patientUUID,
                                                   @PathVariable("encounter-uuid") String encounterUUID) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = encounterService.getEncounterByPatient(patientUUID, encounterUUID);
        if (object == null)
            return new ResponseEntity<>("PATIENT NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer une rencontre par son UUID. Renvoie un 404 Not Found statut si Rencontre n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer " +
            "cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "encounter/{encounter_uuid}/encounterProvider", method = RequestMethod.GET)
    public List<?> getEncounterProvider(@PathVariable("encounter_uuid") String encounter_uuid) {
        return encounterService.getEncounterProviders(encounter_uuid);
    }


    @Operation(summary = "Récupérer les sous-ressources d'un fournisseur de rencontres d'une ressource de rencontres. Renvoie un 404 Not Foundstatut si le fournisseur de " +
            "rencontre n'existe pas. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "encounter/{encounter_uuid}/encounterProvider/{uuid}", method = RequestMethod.GET)
    public List<?> getEncounterProvider(@PathVariable("encounter_uuid") String encounter_uuid,
                                        @PathVariable("uuid") String uuid) {
        return encounterService.getEncounterProvidersUuid(encounter_uuid, uuid);
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateEncounter(@PathVariable String uuid,
                                             @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("ENCOUNTER : " + jsonObject);

            Object object = encounterService.updateEncounter(uuid, jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        System.out.println("VITALS : " + jsonObject.toString());
        return ResponseEntity.ok(jsonObject.toString());
    }

}
