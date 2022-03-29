package com.cg.ams.exception;

@SuppressWarnings("serial")
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
