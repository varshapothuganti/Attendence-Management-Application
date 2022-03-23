package com.cg.ams.exception;

public class RecordNotFoundException extends Exception {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(String message) {
        super(message);
    }

    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }
}
