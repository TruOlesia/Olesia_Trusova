package com.ot.conferences.error;
import lombok.Data;

@Data
public class ValidationError {

    private final String fieldName;

    private final String message;

    public ValidationError(String field, String defaultMessage) {
        this.fieldName = field;
        this.message = defaultMessage;
    }
}
