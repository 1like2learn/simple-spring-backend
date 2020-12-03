package com.personal.simple.exceptions;

public class ResourceFoundException extends RuntimeException {
    
    public ResourceFoundException(String message){

        super("Error from simple API " + message);
    }
}