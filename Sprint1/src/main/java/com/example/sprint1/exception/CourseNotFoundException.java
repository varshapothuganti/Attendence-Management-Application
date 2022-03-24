package com.example.sprint1.exception;

public class CourseNotFoundException extends RuntimeException {

	public CourseNotFoundException() {
		super();
	}

	public CourseNotFoundException(String message) {
		super(message);
	}

	public CourseNotFoundException(Throwable cause) {
		super(cause);
	}

}
