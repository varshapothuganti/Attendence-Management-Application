package com.cg.ams.exception;

public class PasswordDidnotMatchException extends RuntimeException {
    public PasswordDidnotMatchException() {
        super();
    }

    public PasswordDidnotMatchException(String message) {
        super(message);
    }

    public PasswordDidnotMatchException(Throwable cause) {
        super(cause);
    }
}
