package com.formento.exploringmars.infra;

import java.io.Serializable;

class ErrorResult implements Serializable {

    private final String message;

    public ErrorResult(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
