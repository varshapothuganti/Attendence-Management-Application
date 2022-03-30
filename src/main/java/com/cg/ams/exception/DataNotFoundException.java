package com.cg.ams.exception;

@SuppressWarnings("serial")
public class DataNotFoundException extends RuntimeException {

	public DataNotFoundException() {
		super();
	}

	public DataNotFoundException(String message) {
		super(message);
	}

	public DataNotFoundException(Throwable cause) {
		super(cause);
	}

}
