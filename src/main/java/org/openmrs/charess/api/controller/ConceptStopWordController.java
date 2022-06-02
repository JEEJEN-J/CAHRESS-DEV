package org.openmrs.charess.api.controller;

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
public class ConceptStopWordController {

    public ConceptService conceptService;

    @Autowired
    public ConceptStopWordController(ConceptService conceptService) {
        this.conceptService = conceptService;
    }

    @RequestMapping(value = "/conceptStopWord", method = RequestMethod.POST)
    public ResponseEntity<?> conceptStopWord(@RequestBody String responseBody) {
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

    @RequestMapping(value = "/conceptStopWord/{uuid}", method = RequestMethod.GET)
    public List<?> getConceptStopWord(@PathVariable("uuid") String uuid) {
        return conceptService.getConceptStopWord(uuid);
    }
}
