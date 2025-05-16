package org.example.task_tracker.exceptions.impl;

import org.example.task_tracker.exceptions.ErrorResponse;
import org.example.task_tracker.exceptions.IGlobalExceptionHandler;
import org.example.task_tracker.exceptions.extendedExceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice

public class GlobalExceptionHandlerImpl implements IGlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlerNotFound(ResourceNotFoundException e) {
        ErrorResponse error =new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handlerArgumentNotValid(MethodArgumentNotValidException e) {

        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();

        String msg = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(","));

        ErrorResponse error =new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                msg,
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<ErrorResponse> handlerNumberFormat(NumberFormatException e) {
        ErrorResponse error =new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.value(),
                e.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handlerMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e) {
        ErrorResponse error =new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                e.getName() + " must be a number",
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(error);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerException(Exception e) {
        ErrorResponse error =new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                e.getMessage() + e.getClass().getSimpleName(),
                LocalDateTime.now()
        );
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error);
    }
}
