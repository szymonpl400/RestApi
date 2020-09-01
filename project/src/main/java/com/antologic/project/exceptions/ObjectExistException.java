package com.antologic.project.exceptions;

public class ObjectExistException extends RuntimeException {
    public static final String exceptionMessage = "Provided Object already exist";

    public ObjectExistException() {
        super(exceptionMessage);
    }
}
