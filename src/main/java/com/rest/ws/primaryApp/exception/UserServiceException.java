package com.rest.ws.primaryApp.exception;

public class UserServiceException extends RuntimeException {

    private static final long serialVersionUID = -7926355274030157376L;

    public UserServiceException(String message) {
        super(message);
    }
}
