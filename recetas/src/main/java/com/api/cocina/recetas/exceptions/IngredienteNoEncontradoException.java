package com.api.cocina.recetas.exceptions;

public class IngredienteNoEncontradoException extends ApiException{

    public IngredienteNoEncontradoException(String idRecurso) {
        super(String.format("El ingrediente (id=%s) no fue encontrado", idRecurso));
    }

}