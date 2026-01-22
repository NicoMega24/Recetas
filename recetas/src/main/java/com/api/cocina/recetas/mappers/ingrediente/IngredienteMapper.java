package com.api.cocina.recetas.mappers.ingrediente;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;

@Component
public class IngredienteMapper {

    public IngredienteDto toDTO(Ingrediente ingrediente) {
        if (ingrediente == null) {
            return null;
        }

        return new IngredienteDto(
                ingrediente.getId(),
                ingrediente.getNombre(),
                ingrediente.getDescripcion()
        );
    }

    public Ingrediente toEntity(IngredienteDto dto) {
        if (dto == null) {
            return null;
        }

        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setId(dto.id());
        ingrediente.setNombre(dto.nombre());
        ingrediente.setDescripcion(dto.descripcion());
        return ingrediente;
    }

}
