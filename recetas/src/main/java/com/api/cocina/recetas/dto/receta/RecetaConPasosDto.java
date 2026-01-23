package com.api.cocina.recetas.dto.receta;

import java.util.List;

import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.pasos.PasosDto;

public record RecetaConPasosDto(
    Long id,
    String nombre,
    String descripcion,
    Dificultad dificultad,
    Long categoriaId,
    String categoriaNombre,
    List<PasosDto> pasos
) {}

