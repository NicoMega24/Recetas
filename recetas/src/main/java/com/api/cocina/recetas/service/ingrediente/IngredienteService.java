package com.api.cocina.recetas.service.ingrediente;

import java.util.List;

import com.api.cocina.recetas.dto.ingredient.IngredienteDto;

public interface IngredienteService {

    IngredienteDto obtenerIngrediente(Long id);

    List<IngredienteDto> listarIngredientes();

    IngredienteDto crearIngrediente(IngredienteDto dto);

    IngredienteDto actualizarIngrediente(Long id, IngredienteDto dto);

    void eliminarIngrediente(Long id);
}
