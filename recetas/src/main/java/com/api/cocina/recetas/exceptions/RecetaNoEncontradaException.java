package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecetaNoEncontradaException extends RuntimeException {
    public RecetaNoEncontradaException(String idRecurso) {
        super(String.format("La receta (id=%s) no existe", idRecurso));
    }
}