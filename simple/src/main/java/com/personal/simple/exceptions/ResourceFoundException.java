package com.personal.simple.exceptions;

public class ResourceFoundException extends RuntimeException {
    
    /**
     *
     */
    private static final long serialVersionUID = 3849617049190420159L;

    public ResourceFoundException(String message) {

        super("Error from simple API " + message);
    }
}