package com.api.cocina.recetas.exceptions;

public class PasoNoEncontradoException extends ApiException{

    public PasoNoEncontradoException(String idRecurso) {
        super(String.format("Paso (id=%s) no fue encontrado", idRecurso));
    }

}
