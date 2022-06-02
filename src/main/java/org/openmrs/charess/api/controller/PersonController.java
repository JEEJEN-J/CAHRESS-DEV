package org.openmrs.charess.api.controller;


import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import org.json.JSONObject;
import org.openmrs.charess.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/openmrs/ws/rest/v1/person")
@CrossOrigin
public class PersonController {

    public PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }


    @Operation(summary = "Créer une personne, vous devez spécifier les propriétés ci-dessous dans la demande. 401 Unauthorizedest renvoyé si la demande n'est pas authentifiée ou si l'utilisateur authentifié ne dispose pas des autorisations appropriées.")
    @RequestMapping(value = "/createPerson", method = RequestMethod.POST)
    public ResponseEntity<?> createPerson(@RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.createPerson(jsonObject.toString()));
    }

    @Operation(summary = "Créer une sous-ressource de nom de personne pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/createPersonName/{parent-uuid}", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonName(@PathVariable("parent-uuid") String parent_uuid,
                                              @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.createPersonName(parent_uuid, jsonObject.toString()));
    }

    @Operation(summary = "Créer une sous-ressource d'adresse de personne pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/createPersonAddress/{parent-uuid}", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonAddress(@PathVariable("parent-uuid") String parent_uuid,
                                                 @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.createPersonAddress(parent_uuid, jsonObject.toString()));
    }

    @Operation(summary = "créer une sous-ressource d'attribut de personne pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(value = "/createPersonAttribute", method = RequestMethod.POST)
    public ResponseEntity<?> createPersonAttribute(@PathVariable("parent-uuid") String parent_uuid,
                                                   @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.createPersonAttribute(parent_uuid, jsonObject.toString()));
    }


    @Operation(summary = "Récupérer une personne par son UUID. Renvoie un 404 Not Found statut si la personne n'existe pas dans le système. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.GET)
    public ResponseEntity<?> getPersonUuid(@PathVariable("uuid") String uuid) {
        Object object = personService.getPersonByUuid(uuid);
        JSONObject jsonObject = new JSONObject(object.toString().substring(1, object.toString().length() - 1));
        return ResponseEntity.ok(jsonObject.toString());
    }

    @Operation(summary = "Répertorier toutes les sous-ressources de nom de personne correspondant à un fichier target_person_uuid. Renvoie 404 Not Found le statut si la personne n'existe pas. Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/name", method = RequestMethod.GET)
    public List<?> getPersonParentUuidName(@PathVariable("parent-uuid") String parent_uuid) {
        return personService.getPersonByParentUuidName(parent_uuid);
    }

    @Operation(summary = "Lister toutes les adresses de personnes correspondant à un target_person_uuid. Renvoie un 404 Not Foundstatut si l'adresse de la personne n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/address", method = RequestMethod.GET)
    public List<?> getPersonParentUuidAddress(@PathVariable("parent-uuid") String parent_uuid) {
        return personService.getPersonByParentUuidAddress(parent_uuid);
    }

    @Operation(summary = "Répertorier tous les attributs de personne pour une personne donnée. Renvoie a 404 Not Foundsi la personne n'existe pas. S'il n'est pas authentifié ou si l'utilisateur authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/attribute", method = RequestMethod.GET)
    public List<?> getPersonParentUuidAttribute(@PathVariable("parent-uuid") String parent_uuid) {
        return personService.getPersonByParentUuidAttribute(parent_uuid);
    }

    @Operation(summary = "Lister le nom de la personne par son UUID et correspondant à target_person_uuid et a target_person_name_uuid. Renvoie 404 Not Found le statut si la personne n'existe pas. Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/name/{uuid}", method = RequestMethod.GET)
    public List<?> getPersonParentUuidNameUuid(@PathVariable("parent-uuid") String parent_uuid,
                                               @PathVariable("uuid") String uuid) {
        return personService.getPersonByParentUuidNameUuid(parent_uuid, uuid);
    }

    @Operation(summary = "Lister toutes les adresses de personnes par son target_address_uuid et correspondant à un target_person_uuid. Renvoie un 404 Not Found statut si l'adresse de la personne n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/address/{uuid}", method = RequestMethod.GET)
    public List<?> getPersonParentUuidAddressUuid(@PathVariable("parent-uuid") String parent_uuid,
                                                  @PathVariable("uuid") String uuid) {
        return personService.getPersonByParentUuidAddressUuid(parent_uuid, uuid);
    }

    @Operation(summary = "Lister tous les attributs de la personne par son UUID et correspondant à un target_person_uuid. Renvoie un 404 Not Foundstatut si l'attribut de personne n'existe pas. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/attribute/{uuid}", method = RequestMethod.GET)
    public List<?> getPersonParentUuidAttributeUuid(@PathVariable("parent-uuid") String parent_uuid,
                                                    @PathVariable("uuid") String uuid) {
        return personService.getPersonByParentUuidAttributeUuid(parent_uuid, uuid);
    }


    @Operation(summary = "Mettre à jour une personne. Cette méthode modifie uniquement les propriétés spécifiées dans la requête. Renvoie un 404 Not found. Si l'utilisateur n'est pas authentifié ou authentifié ne dispose pas de privilèges suffisants, 401 Unauthorized l'état est renvoyé.")
    @RequestMapping(value = "/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@PathVariable("uuid") String uuid,
                                          @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.updatePerson(uuid, jsonObject.toString()));
    }

    @Operation(summary = "Mettre à jour un nom de personne avec une valeur UUID donnée pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/name/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonName(@PathVariable("parent-uuid") String parent_uuid,
                                              @PathVariable("uuid") String uuid,
                                              @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.updatePersonName(parent_uuid, uuid, jsonObject.toString()));
    }

    @Operation(summary = "Mettre à jour une adresse de personne avec une valeur UUID donnée pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur n'est pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/address/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonAddress(@PathVariable("parent-uuid") String parent_uuid,
                                                 @PathVariable("uuid") String uuid,
                                                 @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.updatePersonAddress(parent_uuid, uuid, jsonObject.toString()));
    }

    @Operation(summary = "Mettre à jour un attribut de personne avec une valeur UUID donnée pour une ressource de personne spécifique, vous devez spécifier les propriétés ci-dessous dans le corps de votre demande. Si l'utilisateur non authentifié ou authentifié ne dispose pas de privilèges suffisants, un 401 Unauthorized statut est renvoyé.")
    @RequestMapping(value = "/{parent-uuid}/attribute/{uuid}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePersonAttribute(@PathVariable("parent-uuid") String parent_uuid,
                                                   @PathVariable("uuid") String uuid,
                                                   @RequestBody String responseBody) {
        JSONObject jsonObject = null;
        JSONParser parser = new JSONParser();
        try {
            String obj = parser.parse(responseBody).toString();
            jsonObject = new JSONObject(obj);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return ResponseEntity.ok(personService.updatePersonAttribute(parent_uuid, uuid, jsonObject.toString()));
    }


}
