package com.cg.ams.exception;

public class UserAuthenticationException extends RuntimeException {
    public UserAuthenticationException() {
    }

    public UserAuthenticationException(String message) {
        super(message);
    }

    public UserAuthenticationException(Throwable cause) {
        super(cause);
    }
}
