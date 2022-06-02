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
public class ConceptAttributeTypeController {

    public ConceptService conceptService;

    @Autowired
    public ConceptAttributeTypeController(ConceptService conceptService){
        this.conceptService = conceptService;
    }

    @Operation(summary = "Pour créer un type d'attribut de concept, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si vous n'êtes pas " +
            "connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptAttributeType", method = RequestMethod.POST)
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


    @Operation(summary = "Filtrez rapidement les types d'attributs de concept avec une requête de recherche donnée. Renvoie un 404 Not Found statut si le type" +
            " d'attribut de concept n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptAttributeTypes", method = RequestMethod.GET)
    public List<?> listConceptAttributeType(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptAttributeType(uuid);
    }


}
