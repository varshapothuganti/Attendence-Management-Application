package com.example.demo.com.cg.ams.exception;




import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.demo.com.cg.ams.entity.EntityErrorResponse;



@ControllerAdvice
public class EntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<EntityErrorResponse> handleException(RecordNotFoundException exception) {
		EntityErrorResponse error = new EntityErrorResponse();
		
		error.setStatus(HttpStatus.NOT_FOUND.value());
		error.setMessage(exception.getMessage());
		//error.setTimeStamp(System.currentTimeMillis());
		error.setTimeStamp(LocalDateTime.now());
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);  //404 Not found
	}
}
