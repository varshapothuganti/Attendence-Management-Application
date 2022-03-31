package com.cg.ams.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ams.entity.AssignFacultyErrorResponse;

/*
 * Exception Handler class for Assign Faculty entity
 * @Author Ramu
 */

@ControllerAdvice
public class AssignFacultyExceptionHandler {
	@ExceptionHandler
	public ResponseEntity<AssignFacultyErrorResponse> handleException(DuplicateRecordException exception) {
		AssignFacultyErrorResponse error = new AssignFacultyErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMsg(exception.getMessage());
		error.setLocalDateTime(LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	@ExceptionHandler
	public ResponseEntity<AssignFacultyErrorResponse> handleException(RecordNotFoundException exception) {
		AssignFacultyErrorResponse error = new AssignFacultyErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMsg(exception.getMessage());
		error.setLocalDateTime(LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
}
