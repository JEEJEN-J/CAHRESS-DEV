package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.openmrs.charess.api.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/visit")
@CrossOrigin
public class VisitController {

    public VisitService visitService;

    @Autowired
    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createVisit(@RequestBody String responseBody) {
        JSONObject jsonObject;
        JSONParser parser = new JSONParser();

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        try {
            jsonObject = new JSONObject(parser.parse(responseBody).toString());
            System.out.println("VISIT TO CREATE : " + jsonObject);

            Object object = visitService.createVisit(jsonObject.toString());
            StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
            jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(ex.getCause(), HttpStatus.FORBIDDEN);
        }
        System.out.println("VISIT CREATED : " + jsonObject);

        return ResponseEntity.ok(jsonObject.toString());
    }

    @Operation(summary = "Récupérer une visite par son UUID. Renvoie un 404 Not Found statut si la visite n'existe pas dans le système. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getVisitUuid(@PathVariable("uuid") String uuid) {
        Object object = visitService.findByUuid(uuid);
        JSONObject jsonObject = new JSONObject(object.toString().substring(1, object.toString().length() - 1));
        return ResponseEntity.ok(jsonObject.toString());
    }

    @Operation(summary = "Récupérer la visite active par UUID du patient. Renvoie un 404 Not Found statut si la visite n'existe pas dans le système. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/patient/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getActiveVisit(@PathVariable("uuid") String uuid) {
        Object object = visitService.findActiveVisit(uuid);
        JSONObject jsonObject = new JSONObject(object.toString().substring(1, object.toString().length() - 1));
        return ResponseEntity.ok(jsonObject.toString());
    }

    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> closeVisit(@RequestBody() String body, @PathVariable("uuid") String uuid) {
        Object object = visitService.closeVisit(uuid, body);
        JSONObject jsonObject = new JSONObject(object.toString().substring(1, object.toString().length() - 1));
        return ResponseEntity.ok(jsonObject.toString());
    }
}
