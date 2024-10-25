package com.api.cocina.recetas.dto.steps;

import java.util.List;

public record PasosDto(
        Long id,
        String descripcion,
        Integer tiempoEstimado,
        Boolean opcional,
        Long receta,
        List<Long> ingredientes ) {

}
