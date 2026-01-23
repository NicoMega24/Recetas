package com.api.cocina.recetas.service.categoria;

import java.util.List;

import com.api.cocina.recetas.dto.categoria.CategoriaDto;

public interface CategoriaService {

    CategoriaDto crear(CategoriaDto dto);

    List<CategoriaDto> listar();

    CategoriaDto buscarPorId(Long id);

    void eliminar(Long id);
}
