package com.api.cocina.recetas.exceptions;

public abstract class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
