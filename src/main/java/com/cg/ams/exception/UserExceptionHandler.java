package com.cg.ams.exception;

import com.cg.ams.entity.UserErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * This class is reponsible for handling all the user defined exceptions
 * <p>
 * Right now it handles:
 * <li>UserNotFoundException</li>
 * <li>UserAuthenticationException</li>
 * <li>PasswordDidnotMatchException</li>
 * </p>
 *
 * @author phanindra
 */
@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exception) {
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMsg(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(UserAuthenticationException exception) {
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMsg(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(PasswordDidnotMatchException exception) {
        UserErrorResponse error = new UserErrorResponse();

        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMsg(exception.getMessage());
        error.setLocalDateTime(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
