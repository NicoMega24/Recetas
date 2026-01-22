package com.api.cocina.recetas.mappers.pasos;

import java.util.List;

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
                pasos.getIngredientes()
                        .stream()
                        .map(Ingrediente::getId)
                        .toList()
        );
    }

    public Pasos toEntity(PasosDto dto) {
        if (dto == null) {
            return null;
        }

        Pasos pasos = new Pasos();
        pasos.setId(dto.id());
        pasos.setDescripcion(dto.descripcion());
        pasos.setTiempoEstimado(dto.tiempoEstimado());
        pasos.setOpcional(dto.opcional());

        if (dto.receta() != null) {
            Receta receta = new Receta();
            receta.setId(dto.receta());
            pasos.setReceta(receta);
        }

        if (dto.ingredientes() != null) {
            List<Ingrediente> ingredientes = dto.ingredientes()
                    .stream()
                    .map(id -> {
                        Ingrediente ingrediente = new Ingrediente();
                        ingrediente.setId(id);
                        return ingrediente;
                    })
                    .toList();

            pasos.setIngredientes(ingredientes);
        }

        return pasos;
    }
}