package com.api.cocina.recetas.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.api.cocina.recetas.dto.errors.GenericError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RecetaNoEncontradaException.class)
    public ResponseEntity<GenericError> handleRecetaNotFound(RecetaNoEncontradaException ex) {
        GenericError error = new GenericError(
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(PasoNoEncontradoException.class)
    public ResponseEntity<GenericError> handlePasoNotFound(PasoNoEncontradoException ex) {
        GenericError error = new GenericError(
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(IngredienteNoEncontradoException.class)
    public ResponseEntity<GenericError> handleIngredienteNotFound(IngredienteNoEncontradoException ex) {
        GenericError error = new GenericError(
                ex.getMessage(),
                null
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleValidation(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errores = ex.getFieldErrors().stream()
                .map(fieldError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(fieldError.getField(), fieldError.getDefaultMessage());
                    return errorMap;
                })
                .toList();

        GenericError errorResponse = new GenericError(
                "Error de validación",
                errores
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericError> handleGeneral(Exception ex) {
        GenericError error = new GenericError(
                "Error interno del servidor",
                null
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }
}
