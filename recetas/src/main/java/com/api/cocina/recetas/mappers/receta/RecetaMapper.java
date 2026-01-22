package com.api.cocina.recetas.mappers.receta;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.recipe.RecetaDto;

@Component
public class RecetaMapper {

    public RecetaDto toDTO(Receta receta) {
        if (receta == null) {
            return null;
        }

        return new RecetaDto(
                receta.getId(),
                receta.getNombre(),
                receta.getDescripcion(),
                receta.getDificultad(),
                receta.getCategoria() != null ? receta.getCategoria().getId() : null
        );
    }

    public Receta toEntity(RecetaDto dto) {
        if (dto == null) {
            return null;
        }

        Categoria categoria = null;
        if (dto.categoria() != null) {
            categoria = new Categoria();
            categoria.setId(dto.categoria());
        }

        Receta receta = new Receta();
        receta.setId(dto.id());
        receta.setNombre(dto.nombre());
        receta.setDescripcion(dto.descripcion());
        receta.setDificultad(dto.dificultad());
        receta.setCategoria(categoria);

        return receta;
    }
}
