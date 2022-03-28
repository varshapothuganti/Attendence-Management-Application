package com.cg.ams.exception;

@SuppressWarnings("serial")
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
