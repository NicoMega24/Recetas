package com.api.cocina.recetas.mappers.pasos;

import java.util.List;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.dto.pasos.PasosDto;

@Component
public class PasosMapper {

    public PasosDto toDto(Pasos pasos) {
        if (pasos == null) return null;

        List<Long> ingredientesIds = pasos.getIngredientes()
                .stream()
                .map(ingrediente -> ingrediente.getId())
                .toList();

        return new PasosDto(
                pasos.getId(),
                pasos.getDescripcion(),
                pasos.getTiempoEstimado(),
                pasos.getOpcional(),
                pasos.getReceta() != null ? pasos.getReceta().getId() : null,
                ingredientesIds
        );
    }
}
