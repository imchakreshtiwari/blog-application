package com.blogapi.blogapi.exception;

public class RecordNotFoundException extends  RuntimeException {
    String resourcename;
    String fieldName;
    long fieldValue;

    String message;

    public RecordNotFoundException(String message){
        super(message);
        this.message = message;
    }
    /**
     * Constructs a new runtime exception with {@code null} as its
     * detail message.  The cause is not initialized, and may subsequently be
     * initialized by a call to {@link #initCause}.
     */
    public RecordNotFoundException(String resourcename, String fieldName, long fieldValue) {
        this.resourcename = resourcename;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
