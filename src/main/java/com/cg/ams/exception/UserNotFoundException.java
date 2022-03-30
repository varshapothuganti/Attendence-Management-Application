package com.cg.ams.exception;

/**
 * This exception is raised when the user is not found in the database.
 *
 * @author phanindra
 */
@SuppressWarnings("serial")
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super();
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }
}
