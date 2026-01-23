package com.api.cocina.recetas.dto.errores;

public record ErroresDto(
    
    String mensaje, 
    int status, 
    String path) {

}
