package com.ot.conferences.error;

import java.util.ArrayList;
import java.util.List;

public class ValidationErrorResponse {
    private List<ValidationError> validationErrors = new ArrayList<>();

    public List<ValidationError> getListErrors() {
        return validationErrors;
    }

}
