package com.api.cocina.recetas.mappers.pasos;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.dto.pasos.PasosDto;

@Component
public class PasosMapper {

    public PasosDto toDTO(Pasos pasos) {
        if (pasos == null) return null;

        return new PasosDto(
                pasos.getId(),
                pasos.getDescripcion(),
                pasos.getTiempoEstimado(),
                pasos.getOpcional(),
                pasos.getReceta() != null ? pasos.getReceta().getId() : null,
                pasos.getIngredientes() != null
                        ? pasos.getIngredientes().stream()
                                .map(i -> i.getId())
                                .collect(Collectors.toList())
                        : null
        );
    }

    public Pasos toEntity(PasosDto dto) {
        if (dto == null) return null;

        Pasos pasos = new Pasos();
        pasos.setId(dto.id());
        pasos.setDescripcion(dto.descripcion());
        pasos.setTiempoEstimado(dto.tiempoEstimado());
        pasos.setOpcional(dto.opcional());
        return pasos;
    }
}
