package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String idRecurso) {
        super( String.format("El proyecto(id=%s) no existe", idRecurso ) );
    }
}
