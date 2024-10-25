package com.api.cocina.recetas.dto.recipe;

import com.api.cocina.recetas.domain.enums.Dificultad;

public record RecetaDto(
    Long id,
    String nombre,
    String descripcion,
    Dificultad dificultad,
    Long categoria
) {

}
