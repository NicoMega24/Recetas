package com.api.cocina.recetas.exceptions;

import org.springframework.http.HttpStatus;

public class IngredienteEnUsoException extends ApiException {

    public IngredienteEnUsoException(Long id) {
        super(
            "No se puede eliminar el ingrediente con id " + id + " porque está asociado a uno o más pasos",
            HttpStatus.CONFLICT
        );
    }
}
