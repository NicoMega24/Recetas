package com.api.cocina.recetas.service.receta;

import java.util.List;

import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;
import com.api.cocina.recetas.dto.recipe.RecetaDto;

public interface RecetaService {
    RecetaDto obtenerReceta(Long id);
    List<RecetaDto> listarRecetas();
    RecetaDto crearReceta(RecetaDto recetaDto);
    RecetaDto actualizarReceta(Long id, RecetaDto recetadto);
    void eliminarReceta(Long id);
    List<IngredienteDto> obtenerIngredientesDeReceta(Long id);
    List<RecetaDto> listarRecetasPorDificultad(Dificultad dificultad);
    Integer obtenerTiempoPreparacionDeReceta(Long id);
}