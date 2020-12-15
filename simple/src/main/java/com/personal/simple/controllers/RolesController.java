package com.personal.simple.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import com.personal.simple.models.Role;
import com.personal.simple.services.RoleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/roles")
public class RolesController {
    
    @Autowired
    RoleService roleService;

    @GetMapping(value = "/roles", produces = "application/json")
    public ResponseEntity<?> listRoles(){

        List<Role> allRoles = roleService.findAll();

        return new ResponseEntity<>(allRoles, HttpStatus.OK);
    }

    @GetMapping(value = "/roles/{roleId}", produces = "application/json")
    public ResponseEntity<?> getRoleById(@PathVariable Long roleId){

        Role role = roleService.findRoleById(roleId);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @GetMapping(value = "/roles/name/{roleName}", produces = "application/json")
    public ResponseEntity<?> getRoleByName(@PathVariable String roleName){

        Role role = roleService.findByName(roleName);

        return new ResponseEntity<>(role, HttpStatus.OK);
    }

    @PostMapping(value = "/role", consumes = "application/json")
    public ResponseEntity<?> addNewRole(@Valid @RequestBody Role newRole){

        newRole.setRoleid(0);
        newRole = roleService.save(newRole);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newRoleURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{roleid}")
            .buildAndExpand(newRole.getRoleid())
            .toUri();
        responseHeaders.setLocation(newRoleURI);

        return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/role/{roleid}", consumes = {"application/json"})
    public ResponseEntity<?> putUpdateRole(@PathVariable long roleid, @Valid @RequestBody Role newRole){

        newRole = roleService.update(roleid, newRole);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}