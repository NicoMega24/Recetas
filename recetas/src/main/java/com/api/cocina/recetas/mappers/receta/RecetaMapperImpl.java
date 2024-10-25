package com.api.cocina.recetas.mappers.receta;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.recipe.RecetaDto;

@Service
public class RecetaMapperImpl implements RecetaMapper {

    @Override
    public RecetaDto toDTO(Receta receta) {
        return new RecetaDto(
            receta.getId(),
            receta.getNombre(),
            receta.getDescripcion(),
            receta.getDificultad(),
            receta.getCategoria().getId()
        );
    }

    @Override
    public Receta toEntity(RecetaDto recetaDto) {
        Categoria categoria = new Categoria();
        categoria.setId(recetaDto.categoria());

        return new Receta(
            recetaDto.id(),
            recetaDto.nombre(),
            recetaDto.descripcion(),
            recetaDto.dificultad(),
            categoria
        );
    }
}