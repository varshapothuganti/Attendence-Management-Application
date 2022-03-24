package com.example.sprint1.exception;

public class DuplicateRecordException extends RuntimeException {

	public DuplicateRecordException() {
		super();
	}

	public DuplicateRecordException(String message) {
		super(message);
	}

	public DuplicateRecordException(Throwable cause) {
		super(cause);
	}
	
	

}
