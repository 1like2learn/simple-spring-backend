package com.personal.simple.controllers;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import com.personal.simple.models.User;
import com.personal.simple.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService userService;

    @GetMapping(value = "roles", produces = "application/json")
    public ResponseEntity<?> listRoles(){

        List<User> allUsers = userService.findAll();

        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping(value = "/users/{userId}", produces = "application/json")
    public ResponseEntity<?> getUserById(@PathVariable Long userId){

        User u = userService.findUserById(userId);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @GetMapping(value = "/user/name/{userName}", produces = "application/json")
    public ResponseEntity<?> getUserByName(@PathVariable String userName){

        User u = userService.findByName(userName);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }

    @GetMapping(value = "/user/name/like/{userName}", produces = "application/json")
    public ResponseEntity<?> getUserLikeName(@PathVariable String userName){
        
        List<User> u = userService.findByNameContaining(userName);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping(value = "/user/name/like/{userName}", produces = "application/json")
    public ResponseEntity<?> addNewUser(@Valid @RequestBody User newuser) throws URISyntaxException {
        
        newuser.setUserid(0);
        newuser = userService.save(newuser);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{userid}")
            .buildAndExpand(newuser.getUserid())
            .toUri();

        responseHeaders.setLocation(newUserURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @PutMapping(value = "/user/{userid}", consumes = "application/json")
    public ResponseEntity<?> updateFullUser(@Valid @RequestBody User updateUser, @PathVariable long userid){

        updateUser.setUserid(userid);
        userService.save(updateUser);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable long id){
        
        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}