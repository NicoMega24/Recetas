package com.api.cocina.recetas.service.receta;

import java.util.List;

import com.api.cocina.recetas.dto.recipe.RecetaDto;
import com.api.cocina.recetas.exceptions.RecetaNoEncontradaException;

public interface RecetaService {
    RecetaDto obtenerReceta(Long id) throws RecetaNoEncontradaException;
    List<RecetaDto> listarRecetas();
    RecetaDto crearReceta(RecetaDto recetaDto);
    RecetaDto actualizarReceta(Long id, RecetaDto receta) throws RecetaNoEncontradaException;
    void eliminarReceta(Long id) throws RecetaNoEncontradaException;
}