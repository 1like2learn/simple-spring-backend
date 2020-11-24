package com.personal.simple.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super("Error from simple API framework: " + message);
    }
}