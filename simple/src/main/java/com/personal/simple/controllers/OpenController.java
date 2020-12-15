package com.personal.simple.controllers;

import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import com.personal.simple.models.User;
import com.personal.simple.models.UserMinimum;
import com.personal.simple.models.UserRoles;
import com.personal.simple.services.RoleService;
import com.personal.simple.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class OpenController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @PostMapping(value = "/createnewuser", consumes = {"application/json"})
    public ResponseEntity<?> addSelf(
        HttpServletRequest httpServletRequest, 
        @Valid @RequestBody UserMinimum minUser
    ) throws URISyntaxException {
        
        // Create new user and add the provided data to it
        User newUser = new User();

        newUser.setUsername(minUser.getUsername());
        newUser.setPassword(minUser.getPassword());
        newUser.setEmail(minUser.getEmail());

        // Add role to the new user
        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newUser, roleService.findByName("USER")));
        newUser.setRoles(newRoles);
        
        newUser = userService.save(newUser);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}