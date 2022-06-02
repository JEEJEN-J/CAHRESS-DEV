package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.openmrs.charess.api.service.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/concept")
@CrossOrigin
public class ConceptMapTypeController {

    public ConceptService conceptService;

    @Autowired
    public ConceptMapTypeController(ConceptService conceptService){
        this.conceptService = conceptService;
    }


    @Operation(summary = "Pour créer un type de carte conceptuelle, vous devez spécifier les attributs ci-dessous dans le corps de la demande. Si vous n'êtes pas connecté pour" +
            " effectuer cette action, un 401 Unauthorized état est renvoyé. Un 400 Bad request statut est renvoyé si le nom est déjà utilisé par un type de carte conceptuelle.")
    @RequestMapping(value = "/conceptMapType", method = RequestMethod.POST)
    public ResponseEntity<?> createConcept(@RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(conceptService.createConcept(jsonObject.toString()));
    }


    @Operation(summary = "Récupérer un type de carte conceptuelle par son UUID. Renvoie un 404 Not Found statut si le type de carte conceptuelle n'existe pas. Si l'utilisateur" +
            " n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptMapType/{uuid}", method = RequestMethod.GET)
    public List<?> getConceptMapType(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptMapType(uuid);
    }
}
