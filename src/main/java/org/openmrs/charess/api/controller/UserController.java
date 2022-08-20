package org.openmrs.charess.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openmrs.charess.api.service.ProviderService;
import org.openmrs.charess.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/user")
@CrossOrigin
public class UserController {

    public UserService userService;
    public ProviderService providerService;


    @Autowired
    public UserController(UserService userService, ProviderService providerService) {
        this.userService = userService;
        this.providerService = providerService;
    }

    @Operation(summary = "Filtrez rapidement les utilisateurs avec des paramètres de requête donnés. Ajoutez un paramètre includeAll=truepour récupérer également les utilisateurs désactivés. Renvoie un 404 Not Foundstatut si l'utilisateur n'existe pas. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getUsers() {
        Object usersData = userService.getAllUsers();
        JSONArray jsonArray = new JSONArray(usersData.toString());
        JSONObject usersJSON = jsonArray.getJSONObject(0);
        return ResponseEntity.ok(usersJSON.toString());
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ResponseEntity<?> logout() throws IOException {
        Object usersData = userService.logout();
        JSONArray jsonArray = new JSONArray(usersData.toString());
        return ResponseEntity.ok(jsonArray.toString());
    }


    @Operation(summary = "Le jeton de session est récupéré à l'aide de l'authentification de base sur le point de /session terminaison.")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@RequestParam("username") String username,
                                     @RequestParam("password") String password) {

        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);

        Object object = userService.authenticate(username, password);
        JSONArray sessonJson = new JSONArray(object.toString());

        JSONObject jsonObject = sessonJson.getJSONObject(0).getJSONObject("user");

        Object providers = providerService.allProviders();
        JSONArray jsonArray = new JSONArray(providers.toString()).getJSONObject(0).getJSONArray("results");

        String providerIdentifier = "";
        JSONObject jsonProvider;

        for (int i = 0; i < jsonArray.length(); i++) {
            providerIdentifier = (jsonArray.getJSONObject(i).get("display").toString().split("-"))[0];
            jsonProvider = jsonArray.getJSONObject(i);
            if (username.equalsIgnoreCase("admin") && (providerIdentifier.trim().equalsIgnoreCase("UNKNOWN"))) {
                jsonObject.append("provider", jsonProvider);
                break;
            } else if (providerIdentifier.trim().equalsIgnoreCase(username)) {
                jsonObject.append("provider", jsonProvider);
                break;
            }
        }
        if (object == Collections.singletonList(401))
            return new ResponseEntity<>("username", textPlainHeaders, HttpStatus.UNAUTHORIZED);
        return ResponseEntity.ok(jsonObject.toString());
    }

    @Operation(summary = "Récupérer un utilisateur par son UUID. Renvoie un 404 Not Foundstatut si l'utilisateur n'existe pas. Si vous n'êtes pas connecté pour effectuer cette action, un 401 Unauthorized état est renvoyé.")
    @RequestMapping(value = "/uuid", method = RequestMethod.GET)
    public ResponseEntity<?> getByUuid(@RequestParam("uuid") String uuid) throws ParseException {
        HttpHeaders textPlainHeaders = new HttpHeaders();
        textPlainHeaders.setContentType(MediaType.TEXT_PLAIN);
        Object object = userService.findByUuid(uuid);
        if (object == null)
            return new ResponseEntity<>("USER NOT-FOUND : ", textPlainHeaders, HttpStatus.UNAUTHORIZED);
        StringBuilder builder = new StringBuilder(object.toString().substring(1, object.toString().length() - 1));
        JSONParser parser = new JSONParser();
        JSONObject jsonObject = new JSONObject(parser.parse(builder.toString()).toString());
        return ResponseEntity.ok(jsonObject.toString());
    }

}
