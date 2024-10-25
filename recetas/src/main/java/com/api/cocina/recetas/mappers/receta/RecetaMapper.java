package com.api.cocina.recetas.mappers.receta;

import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.recipe.RecetaDto;

public interface RecetaMapper {

    RecetaDto toDTO(Receta receta);
    Receta toEntity(RecetaDto recetaDto);

}
