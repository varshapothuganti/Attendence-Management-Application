package com.cg.ams.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ams.entity.AttendanceErrorResponse;

@ControllerAdvice
public class AttendanceExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<AttendanceErrorResponse> handleException(DataNotFoundException exception) {
		AttendanceErrorResponse error = new AttendanceErrorResponse();

		error.setStatus(HttpStatus.BAD_REQUEST.value());
		error.setMsg(exception.getMessage());
		error.setLocalDateTime(LocalDateTime.now());

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
