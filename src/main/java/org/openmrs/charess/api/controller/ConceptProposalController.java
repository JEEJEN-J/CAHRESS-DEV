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
public class ConceptProposalController {

    public ConceptService conceptService;

    @Autowired
    public ConceptProposalController(ConceptService conceptService){
        this.conceptService = conceptService;
    }


    @Operation(summary = "Pour créer une proposition de concept, vous devez spécifier les attributs ci-dessous dans le corps de la demande. Si vous n'êtes pas connecté pour " +
            "effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptProposal", method = RequestMethod.POST)
    public ResponseEntity<?> conceptProposal(@RequestBody String responseBody) {
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


    @Operation(summary = "Récupérer une proposition de concept par son UUID. Renvoie un 404 Not Found statut si la proposition n'existe pas. Si l'utilisateur n'est pas connecté " +
            "pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptProposal/{uuid}", method = RequestMethod.GET)
    public List<?> getConceptProposal(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptMapType(uuid);
    }
}
