package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.openmrs.charess.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/personattributetype")
@CrossOrigin
public class PersonAttributeType {

    public PersonService personService;

    @Autowired
    public PersonAttributeType(PersonService personService) {
        this.personService = personService;
    }

    @Operation(summary = "Créer un type d'attribut de personne, vous devez spécifier les attributs ci-dessous dans le corps de la demande. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/createPersonAttributeType", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody String responseBody) {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Object objects = personService.createPersonAttributeType(jsonObject.toString());
        return ResponseEntity.ok(objects);
    }

    @Operation(summary = "Récupérer un type d'attribut de personne par son UUID. Renvoie un 404 Not Found statut si le type d'attribut de personne n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public List<?> getPersonUuid(@PathVariable("uuid") String uuid) {
        return personService.getPersonAttributeTypeByUuid(uuid);
    }
}
