package com.cg.ams.exception;

/**
 * This exception is thrown when the password did not match
 *
 * @author phanindra
 */
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
