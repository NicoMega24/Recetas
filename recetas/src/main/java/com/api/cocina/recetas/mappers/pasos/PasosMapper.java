package com.api.cocina.recetas.mappers.pasos;

import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.dto.steps.PasosDto;

public interface PasosMapper {
    PasosDto toDTO(Pasos pasos);
    Pasos toEntity(PasosDto pasosDto);
}
