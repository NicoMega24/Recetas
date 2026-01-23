package com.api.cocina.recetas.service.receta;

import java.util.List;

import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingrediente.IngredienteSimpleDto;
import com.api.cocina.recetas.dto.receta.RecetaDto;
import com.api.cocina.recetas.dto.receta.RecetaResumenDto;

public interface RecetaService {

    RecetaDto obtenerReceta(Long id);

    List<RecetaDto> listarRecetas();

    RecetaDto crearReceta(RecetaDto recetaDto);

    RecetaDto actualizarReceta(Long id, RecetaDto recetaDto);

    void eliminarReceta(Long id);

    List<IngredienteSimpleDto> obtenerIngredientesDeReceta(Long id);

    List<RecetaDto> listarRecetasPorDificultad(Dificultad dificultad);

    List<RecetaResumenDto> listarRecetasPorCategoria(Long categoriaId);

    List<IngredienteSimpleDto> obtenerIngredientesDePaso(Long recetaId, Long pasoId);

    Integer obtenerTiempoPreparacionDeReceta(Long id);
}
