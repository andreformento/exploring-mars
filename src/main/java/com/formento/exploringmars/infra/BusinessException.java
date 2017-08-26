package com.formento.exploringmars.infra;

public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

}
