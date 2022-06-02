package org.openmrs.charess.api.controller;

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
}
