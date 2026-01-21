package com.api.cocina.recetas.exceptions;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.api.cocina.recetas.dto.errors.GenericError;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<GenericError> handleApiException(ApiException ex, WebRequest request) {

        GenericError error = new GenericError(
                ex.getMessage(),
                null
        );

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericError> handleValidation(MethodArgumentNotValidException ex) {

        var errorList = ex.getFieldErrors().stream()
                .map(campoError -> {
                    Map<String, String> errorMap = new HashMap<>();
                    errorMap.put(campoError.getField(), campoError.getDefaultMessage());
                    return errorMap;
                }).toList();

        GenericError erroresDto = new GenericError(
                "Error de validación",
                errorList
        );

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(erroresDto);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GenericError> handleGeneral(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericError("Error interno del servidor", null));
    }
}
