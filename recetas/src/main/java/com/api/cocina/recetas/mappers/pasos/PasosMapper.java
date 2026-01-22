package com.api.cocina.recetas.mappers.pasos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.steps.PasosDto;

@Component
public class PasosMapper {

    public PasosDto toDTO(Pasos pasos) {
        if (pasos == null) {
            return null;
        }

        return new PasosDto(
                pasos.getId(),
                pasos.getDescripcion(),
                pasos.getTiempoEstimado(),
                pasos.getOpcional(),
                pasos.getReceta() != null ? pasos.getReceta().getId() : null,
                pasos.getIngredientes() != null
                        ? pasos.getIngredientes().stream()
                            .map(Ingrediente::getId)
                            .collect(Collectors.toList())
                        : List.of()
        );
    }

    public Pasos toEntity(PasosDto dto) {
        if (dto == null) {
            return null;
        }

        Receta receta = null;
        if (dto.receta() != null) {
            receta = new Receta();
            receta.setId(dto.receta());
        }

        List<Ingrediente> ingredientes = dto.ingredientes() != null
                ? dto.ingredientes().stream()
                    .map(id -> {
                        Ingrediente ingrediente = new Ingrediente();
                        ingrediente.setId(id);
                        return ingrediente;
                    })
                    .collect(Collectors.toList())
                : List.of();

        return new Pasos(
                dto.id(),
                dto.descripcion(),
                dto.tiempoEstimado(),
                dto.opcional(),
                receta,
                ingredientes
        );
    }
}
