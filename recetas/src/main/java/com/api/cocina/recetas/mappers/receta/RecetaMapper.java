package com.api.cocina.recetas.mappers.receta;

import java.util.List;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.receta.RecetaConPasosDto;
import com.api.cocina.recetas.dto.receta.RecetaDto;
import com.api.cocina.recetas.dto.receta.RecetaResumenDto;
import com.api.cocina.recetas.mappers.pasos.PasosMapper;

@Component
public class RecetaMapper {

    private final PasosMapper pasosMapper;

    public RecetaMapper(PasosMapper pasosMapper) {
        this.pasosMapper = pasosMapper;
    }

    public RecetaDto toDto(Receta receta) {
        if (receta == null) return null;

        return new RecetaDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                receta.getCategoria() != null ? receta.getCategoria().getId() : null
        );
    }

    public RecetaResumenDto toResumenDto(Receta receta, Integer tiempoTotal) {
        if (receta == null) return null;

        return new RecetaResumenDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                tiempoTotal
        );
    }

    public RecetaConPasosDto toConPasosDto(Receta receta) {
        if (receta == null) return null;

        List pasosDto = receta.getPasos()
                .stream()
                .map(pasosMapper::toDto)
                .toList();

        return new RecetaConPasosDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                receta.getCategoria() != null ? receta.getCategoria().getId() : null,
                receta.getCategoria() != null ? receta.getCategoria().getNombre() : null,
                pasosDto
        );
    }
}
