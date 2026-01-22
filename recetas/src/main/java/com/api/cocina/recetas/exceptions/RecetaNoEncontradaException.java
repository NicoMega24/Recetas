package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;

public class RecetaNoEncontradaException extends ApiException {

    public RecetaNoEncontradaException(Long id) {
        super("La receta (id=" + id + ") no existe", HttpStatus.NOT_FOUND);
    }
}
