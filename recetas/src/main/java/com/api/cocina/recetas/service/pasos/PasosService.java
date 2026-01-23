package com.api.cocina.recetas.service.pasos;

import java.util.List;

import com.api.cocina.recetas.dto.pasos.PasosDto;

public interface PasosService {

    PasosDto crearPaso(PasosDto pasosDto);

    List<PasosDto> listarPasosPorReceta(Long recetaId);

    PasosDto obtenerPaso(Long id);

    PasosDto actualizarPaso(Long id, PasosDto pasosDto);

    void eliminarPaso(Long id);
}
