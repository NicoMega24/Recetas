package com.api.cocina.recetas.service.pasos;

import java.util.List;

import com.api.cocina.recetas.dto.steps.PasosDto;

public interface PasosService {
    PasosDto crearPaso(PasosDto pasosDto);
    List<PasosDto> listarPasos();
    PasosDto obtenerPaso(Long id);
    PasosDto actualizarPaso(Long id, PasosDto pasosDto);
    void eliminarPaso(Long id);
}