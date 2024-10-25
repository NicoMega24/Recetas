package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PasoNoEncontradoException extends RuntimeException{

    public PasoNoEncontradoException(String idRecurso) {
        super(String.format("Paso (id=%s) no fue encontrado", idRecurso));
    }

}
