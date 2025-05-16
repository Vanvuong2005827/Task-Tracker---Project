package org.example.task_tracker.exceptions;

import org.example.task_tracker.exceptions.extendedExceptions.ResourceNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

public interface IGlobalExceptionHandler {
    ResponseEntity<ErrorResponse> handlerNotFound(ResourceNotFoundException e);
    ResponseEntity<ErrorResponse> handlerArgumentNotValid(MethodArgumentNotValidException e);
    ResponseEntity<ErrorResponse> handlerNumberFormat(NumberFormatException e);
    ResponseEntity<ErrorResponse> handlerMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException e);
//    ResponseEntity<ErrorResponse> handlerDataIntegrityViolation(DataIntegrityViolationException e);
    ResponseEntity<ErrorResponse> handlerException(Exception e);
}
