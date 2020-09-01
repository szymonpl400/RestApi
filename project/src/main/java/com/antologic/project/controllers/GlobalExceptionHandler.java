package com.antologic.project.controllers;

import com.antologic.project.exceptions.NotFoundException;
import com.antologic.project.exceptions.ObjectExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleNotFoundException() {
        return new ResponseEntity<>(NotFoundException.exceptionMessage, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(ObjectExistException.class)
    public final ResponseEntity<Object> handleObjectExistException() {
        return new ResponseEntity<>(ObjectExistException.exceptionMessage, HttpStatus.CONFLICT);
    }

}
