package com.example.sprint1.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.sprint1.bean.CourseErrorResponse;

@ControllerAdvice
public class CourseExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<CourseErrorResponse> handleException(CourseNotFoundException exception) {
		CourseErrorResponse error = new CourseErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
	}
	
	@ExceptionHandler
	public ResponseEntity<CourseErrorResponse> handleException(DuplicateRecordException exception) {
		CourseErrorResponse error = new CourseErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
	}

}
