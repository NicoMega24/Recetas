package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;

public class IngredienteNoEncontradoException extends ApiException {

    public IngredienteNoEncontradoException(Long id) {
        super("El ingrediente (id=" + id + ") no fue encontrado", HttpStatus.NOT_FOUND);
    }
}
