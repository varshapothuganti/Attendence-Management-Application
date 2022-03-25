package com.cg.ams.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ams.entity.EntityErrorResponse;

import java.time.LocalDateTime;


@ControllerAdvice
public class EntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(RecordNotFoundException exception) {
        EntityErrorResponse error = new EntityErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exception.getMessage());
        //error.setTimeStamp(System.currentTimeMillis());
        error.setTimeStamp(LocalDateTime.now());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<EntityErrorResponse> handleException(DuplicateRecordException ex) {
        EntityErrorResponse ere = new EntityErrorResponse();
        ere.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
        ere.setMessage(ex.getMessage());
        ere.setTimeStamp(LocalDateTime.now());
        return new ResponseEntity<>(ere, HttpStatus.UNPROCESSABLE_ENTITY);
    }

}
