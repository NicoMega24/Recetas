package com.api.cocina.recetas.dto.recipe;

import java.util.List;

import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.steps.PasosDto;

public record RecetaConPasosDto(
    Long id,
    String nombre,
    String descripcion,
    Dificultad dificultad,
    Long categoriaId,
    String categoriaNombre,
    List<PasosDto> pasos
) {}

