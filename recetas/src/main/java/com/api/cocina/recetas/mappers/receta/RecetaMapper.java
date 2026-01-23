package com.api.cocina.recetas.mappers.receta;

import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.receta.RecetaConPasosDto;
import com.api.cocina.recetas.dto.receta.RecetaDto;
import com.api.cocina.recetas.mappers.pasos.PasosMapper;

@Component
public class RecetaMapper {

    private final PasosMapper pasosMapper;

    public RecetaMapper(PasosMapper pasosMapper) {
        this.pasosMapper = pasosMapper;
    }

    // DTO simple (sin pasos)
    public RecetaDto toDTO(Receta receta) {
        return new RecetaDto(
            receta.getId(),
            receta.getNombre(),
            receta.getDescripcion(),
            receta.getDificultad(),
            receta.getCategoria() != null ? receta.getCategoria().getId() : null
        );
    }

    // Para crear/actualizar entidad desde DTO
    public Receta toEntity(RecetaDto dto) {
        Receta receta = new Receta();
        receta.setId(dto.id());
        receta.setNombre(dto.nombre());
        receta.setDescripcion(dto.descripcion());
        receta.setDificultad(dto.dificultad());
        // La categoría se asigna en el service con un objeto Categoria con solo ID
        return receta;
    }

    // DTO completo con pasos e ingredientes
    public RecetaConPasosDto toRecetaConPasosDTO(Receta receta) {
        return new RecetaConPasosDto(
            receta.getId(),
            receta.getNombre(),
            receta.getDescripcion(),
            receta.getDificultad(),
            receta.getCategoria() != null ? receta.getCategoria().getId() : null,
            receta.getCategoria() != null ? receta.getCategoria().getNombre() : null,
            receta.getPasos().stream()
                   .map(pasosMapper::toDTO)
                   .collect(Collectors.toList())
        );
    }
}
