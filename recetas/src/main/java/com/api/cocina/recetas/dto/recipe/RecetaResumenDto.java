package com.api.cocina.recetas.dto.recipe;

import com.api.cocina.recetas.domain.enums.Dificultad;

public record RecetaResumenDto(
    
    Long id,
    String nombre,
    String descripcion,
    Dificultad dificultad,
    Integer tiempoTotal

) {

}
