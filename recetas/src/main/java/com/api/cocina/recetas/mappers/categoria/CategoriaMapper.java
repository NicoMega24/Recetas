package com.api.cocina.recetas.mappers.categoria;

import org.springframework.stereotype.Component;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.dto.categoria.CategoriaDto;

@Component
public class CategoriaMapper {

    public CategoriaDto toDto(Categoria categoria) {
        if (categoria == null) return null;

        return new CategoriaDto(
                categoria.getId(),
                categoria.getNombre()
        );
    }

    public Categoria toEntity(CategoriaDto dto) {
        if (dto == null) return null;

        Categoria categoria = new Categoria();
        categoria.setId(dto.getId());
        categoria.setNombre(dto.getNombre());
        return categoria;
    }
}
