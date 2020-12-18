package com.personal.simple.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    /**
     *
     */
    private static final long serialVersionUID = -6820048101206984179L;

    public ResourceNotFoundException(String message) {
        super("Error from simple API framework: " + message);
    }
}