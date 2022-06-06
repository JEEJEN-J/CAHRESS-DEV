package org.openmrs.charess.api.controller;

import org.json.JSONArray;
import org.openmrs.charess.api.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/openmrs/ws/rest/v1/role")
@CrossOrigin
public class RoleController {

    public RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getRoles() {
        Object object = roleService.getAllRoles();
        JSONArray jsonArray = new JSONArray(object.toString());
        return ResponseEntity.ok(jsonArray.toString());
    }


}
