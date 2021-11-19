package com.ot.conferences.controller;

import com.ot.conferences.error.ValidationError;
import com.ot.conferences.error.ValidationErrorResponse;
import com.ot.conferences.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@RestControllerAdvice
public class ErrorHandlingController extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
            ConstraintViolationException e) {

        log.error("Validation error", e);

        ValidationErrorResponse error = new ValidationErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()) {
            error.getListErrors().add(
                    new ValidationError(violation.getPropertyPath().toString(), violation.getMessage()));
        }

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public final ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("Not found error", ex);
        return new ResponseEntity("Resource not found", NOT_FOUND);
    }
}
