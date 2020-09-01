package com.antologic.project.exceptions;

public class NotFoundException extends RuntimeException {
    public static final String exceptionMessage = "Provided Object not exist";

    public NotFoundException() {
        super(exceptionMessage);
    }
}
