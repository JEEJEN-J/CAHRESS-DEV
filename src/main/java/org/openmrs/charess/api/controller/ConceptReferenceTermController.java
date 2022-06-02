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
public class ConceptReferenceTermController {

    public ConceptService conceptService;

    @Autowired
    public ConceptReferenceTermController(ConceptService conceptService){
        this.conceptService = conceptService;
    }


    @Operation(summary = "Pour créer un terme de référence de concept, vous devez spécifier les attributs ci-dessous dans le corps de la requête. Si vous n'êtes pas connecté pour" +
            " effectuer cette action, un 401 Unauthorized état est renvoyé. Un 400 Bad Request statut est renvoyé si le code utilisé est déjà utilisé par un autre terme de référence de concept avec la même source de concept")
    @RequestMapping(value = "/conceptReferenceTerm", method = RequestMethod.POST)
    public ResponseEntity<?> conceptReferenceTerm(@RequestBody String responseBody) {
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


    @Operation(summary = "Récupérer un terme de référence de concept par son UUID. Renvoie un 404 Not Found statut si le terme de référence de concept n'existe pas. " +
            "Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptReferenceTerm/{uuid}", method = RequestMethod.GET)
    public List<?> getConceptReferenceTerm(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptReferenceTerm(uuid);
    }

}
