package org.openmrs.charess.api.controller;

import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.openmrs.charess.api.service.ConceptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/openmrs/ws/rest/v1/concept")
@CrossOrigin
public class ConceptController {

    public ConceptService conceptService;

    @Autowired
    public ConceptController(ConceptService conceptService){
        this.conceptService = conceptService;
    }

    @RequestMapping(value = "/concept", method = RequestMethod.POST)
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

}
