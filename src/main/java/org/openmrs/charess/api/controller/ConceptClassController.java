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
public class ConceptClassController {

    public ConceptService conceptService;

    @Autowired
    public ConceptClassController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @Operation(summary = "Pour créer une classe de concept, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si vous n'êtes pas connecté pour effectuer" +
            " cette action, un 401 Unauthorized état est renvoyé. Un 400 Bad Request statut est renvoyé si le nouveau nom de classe de concept est déjà utilisé pour une autre classe de concept")
    @RequestMapping(value = "/conceptClass", method = RequestMethod.POST)
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


    @Operation(summary = "Récupérer une classe de concept par son UUID. Renvoie un 404 Not Foundstatut si le type de classe de concept n'existe pas. Si l'utilisateur n'est" +
            " pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptClass/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getConceptClass(@PathVariable("uuid") String uuid) {
        return ResponseEntity.ok(conceptService.getConceptClass(uuid));
    }


    @Operation(summary = "Répertorier toutes les classes de concept avec une requête de recherche donnée. Renvoie un 404 Not Found statut si les classes de concept n'existent pas." +
            " Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptClass/{limit}", method = RequestMethod.GET)
    public List<?> allConceptClass(@PathVariable("limit") Integer limit) {
        return conceptService.listConceptClass(limit);
    }
}
