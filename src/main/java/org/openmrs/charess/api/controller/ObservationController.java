package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openmrs.charess.api.service.ObservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/openmrs/ws/rest/v1/obs")
@CrossOrigin
public class ObservationController {

    public ObservationService observationService;

    @Autowired
    public ObservationController(ObservationService observationService) {
        this.observationService = observationService;
    }


    @Operation(summary = "Pour créer une observation, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si l'utilisateur non authentifié ou authentifié" +
            " ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createObs(@RequestBody String responseBody) {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();
        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("OBS TO CREATE : " + responseBody);

            Object object = observationService.createObs(responseBody);
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        System.out.println("OBS CREATED : " + jsonObject);
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Récupérer une observation par son UUID. Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, " +
            "un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getObs(@PathVariable("uuid") String uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = observationService.getObsByUuid(uuid);
        if (object == null)
            return new ResponseEntity<>("OBS NOT-FOUND : ", textPlainHeaders, HttpStatus.NO_CONTENT);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }


    @Operation(summary = "Mettre à jour un obs cible, cette méthode ne modifie que les propriétés de la requête. Renvoie 404 Not Found le statut si l'observation n'existe pas." +
            " Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<?> updateObs(@RequestBody String responseBody) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        try {
            JSONArray jsonArray = new JSONArray(responseBody);
            System.out.println("JSONArray : " + jsonArray);
            System.out.println("JSONArray.LENGTH : " + jsonArray.length());
            for (int i = 0; i < jsonArray.length(); i++) {
                String obs_uuid = jsonArray.getJSONObject(i).get("obs").toString();
                jsonArray.getJSONObject(i).remove("obs");
                System.out.println("OBJECT : " + jsonArray.getJSONObject(i).toString());
                Object object = observationService.updateObs(obs_uuid, jsonArray.getJSONObject(i).toString());
                if (object == null)
                    return new ResponseEntity<>("ENCOUNTER : ", textPlainHeaders, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return ResponseEntity.ok(ex.getCause());
        }
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

}
