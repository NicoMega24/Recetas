package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class IngredienteNoEncontradoException extends RuntimeException{

    public IngredienteNoEncontradoException(String idRecurso) {
        super(String.format("El ingrediente (id=%s) no fue encontrado", idRecurso));
    }

}