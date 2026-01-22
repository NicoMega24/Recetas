package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;

public class PasoNoEncontradoException extends ApiException {

    public PasoNoEncontradoException(Long id) {
        super("El paso (id=" + id + ") no fue encontrado", HttpStatus.NOT_FOUND);
    }
}
