package com.cg.ams.exception;

@SuppressWarnings("serial")
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
