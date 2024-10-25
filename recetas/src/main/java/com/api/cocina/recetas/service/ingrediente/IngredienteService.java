package com.api.cocina.recetas.service.ingrediente;

import java.util.List;

import com.api.cocina.recetas.dto.ingredient.IngredienteDto;

public interface IngredienteService {
    IngredienteDto crearIngrediente(IngredienteDto ingredienteDto);
    List<IngredienteDto> listarIngredientes();
    IngredienteDto obtenerIngrediente(Long id);
    IngredienteDto actualizarIngrediente(Long id, IngredienteDto ingredienteDto);
    void eliminarIngrediente(Long id);
}
