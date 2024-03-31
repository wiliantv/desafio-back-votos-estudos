package com.wiliantv.votacao.exceptions.responses;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.Getter;

import java.util.List;
@Getter
public class ErrosValidation {
    private String message;
    private List<ErrosValidationParameters> parameters;

    public ErrosValidation(ConstraintViolationException ex){
        this. parameters = ex.getConstraintViolations().stream().map(ErrosValidationParameters::new).toList();
        this.message = ex.getMessage();
    }

    @Getter
    public class ErrosValidationParameters{
        private String field;
        private String message;


        public ErrosValidationParameters(ConstraintViolation<?> constraintViolation) {
            this.message = constraintViolation.getMessage();
            this.field = constraintViolation.getPropertyPath().toString();
        }
    }
}
