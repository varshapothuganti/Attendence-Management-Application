package com.cg.ams.exception;

public class StudentDuplicateRecordException extends RuntimeException{
	
    public StudentDuplicateRecordException() {
        super();
    }

    public StudentDuplicateRecordException(String message) {
        super(message);
    }

    public StudentDuplicateRecordException(Throwable cause) {
        super(cause);
    }

}
