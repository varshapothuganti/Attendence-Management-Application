package com.cg.ams.exception;

/**
 * This exception is thrown when the authentication is failed.
 * <p>
 * Here passing authentication means:
 * <li>Having same login ID</li>
 * <li>Having same password</li>
 * </p>
 *
 * @author phanindra
 */
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
