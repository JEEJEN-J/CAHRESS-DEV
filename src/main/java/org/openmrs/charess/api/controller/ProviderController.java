package org.openmrs.charess.api.controller;

import org.openmrs.charess.api.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/provider")
@CrossOrigin
public class ProviderController {

    @Autowired
    public ProviderService providerService;

    @RequestMapping(method= RequestMethod.GET)
    public ResponseEntity<?> getByUuid(@RequestParam("user") String user) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = providerService.getProviderByUser(user);
        if(object == null)
            return new ResponseEntity<>("USER", textPlainHeaders, HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(object);
    }

}
