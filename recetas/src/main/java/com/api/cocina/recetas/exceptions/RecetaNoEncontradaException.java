package com.api.cocina.recetas.exceptions;

public class RecetaNoEncontradaException extends ApiException {
    public RecetaNoEncontradaException(String idRecurso) {
        super(String.format("La receta (id=%s) no existe", idRecurso));
    }
}
