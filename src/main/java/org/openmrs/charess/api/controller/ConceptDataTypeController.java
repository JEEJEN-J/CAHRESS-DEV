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
public class ConceptDataTypeController {

    public ConceptService conceptService;

    @Autowired
    public ConceptDataTypeController(ConceptService conceptService){
        this.conceptService = conceptService;
    }


    @Operation(summary = "Récupérer un type de données de concept par son UUID. Renvoie un 404 Not Found statut si le type de données de concept n'existe pas. Si l'utilisateur " +
            "n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/conceptDataType/{uuid}", method = RequestMethod.GET)
    public List<?> getConceptDataType(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptDataType(uuid);
    }
}
