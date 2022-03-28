package com.cg.ams.exception;

import java.time.LocalDateTime;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.cg.ams.entity.EntityErrorResponse;


@ControllerAdvice
public class StudentExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(RecordNotFoundException exception)
	{
		EntityErrorResponse error=new EntityErrorResponse();
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 	//408 Not found
		
		
	}
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(DuplicateRecordException exception)
	{
		EntityErrorResponse error=new EntityErrorResponse();
		error.setStatus(HttpStatus.UNPROCESSABLE_ENTITY.value());
		error.setMessage(exception.getMessage());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);  //409 Conflict
		
		
	}


}
