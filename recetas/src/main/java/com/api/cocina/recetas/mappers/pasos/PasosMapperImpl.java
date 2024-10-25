package com.api.cocina.recetas.mappers.pasos;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.steps.PasosDto;

@Service
public class PasosMapperImpl implements PasosMapper {
    
    @Override
    public PasosDto toDTO(Pasos pasos) {
        return new PasosDto(
            pasos.getId(),
            pasos.getDescripcion(),
            pasos.getTiempoEstimado(),
            pasos.getOpcional(),
            pasos.getReceta().getId(),
            pasos.getIngredientes().stream().map(Ingrediente::getId).collect(Collectors.toList())
        );
    }
    
    @Override
    public Pasos toEntity(PasosDto pasosDto) {
        Receta receta = new Receta();
        receta.setId(pasosDto.receta());
        
        List<Ingrediente> ingredientes = pasosDto.ingredientes().stream()
            .map(id -> {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(id);
                return ingrediente;
            })
            .collect(Collectors.toList());
        
        return new Pasos(
            pasosDto.id(),
            pasosDto.descripcion(),
            pasosDto.tiempoEstimado(),
            pasosDto.opcional(),
            receta,
            ingredientes
            );
        }
}