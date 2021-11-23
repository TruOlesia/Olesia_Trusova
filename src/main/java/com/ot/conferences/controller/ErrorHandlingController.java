package com.ot.conferences.controller;

import com.ot.conferences.error.ValidationError;
import com.ot.conferences.error.ValidationErrorResponse;
import com.ot.conferences.exception.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;


@Slf4j
@RestControllerAdvice
public class ErrorHandlingController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> onConstraintValidationException(
            MethodArgumentNotValidException e) {

        log.error("Validation error", e);

        ValidationErrorResponse error = new ValidationErrorResponse();
        for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
            error.getListErrors().add(
                    new ValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
        }

        return new ResponseEntity<>(error, BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(NotFoundException ex, WebRequest request) {
        log.error("Not found error", ex);
        return new ResponseEntity("Resource not found", NOT_FOUND);
    }
}
