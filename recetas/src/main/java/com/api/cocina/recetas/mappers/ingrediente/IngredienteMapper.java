package com.api.cocina.recetas.mappers.ingrediente;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;

public interface IngredienteMapper {
    IngredienteDto toDTO(Ingrediente ingrediente);
    Ingrediente toEntity(IngredienteDto ingredienteDto);
}