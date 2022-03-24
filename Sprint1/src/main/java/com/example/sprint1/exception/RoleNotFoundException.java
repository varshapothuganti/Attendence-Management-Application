package com.example.sprint1.exception;

public class RoleNotFoundException extends RuntimeException {

	public RoleNotFoundException() {
		super();
	}

	public RoleNotFoundException(String message) {
		super(message);
	}

	public RoleNotFoundException(Throwable cause) {
		super(cause);	
	}

}
