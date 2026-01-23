package com.api.cocina.recetas.service.pasos.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.dto.steps.PasosDto;
import com.api.cocina.recetas.exceptions.PasoNoEncontradoException;
import com.api.cocina.recetas.mappers.pasos.PasosMapper;
import com.api.cocina.recetas.repository.pasos.PasosRepository;
import com.api.cocina.recetas.service.pasos.PasosService;

@Service
public class PasosServiceImpl implements PasosService {

    private final PasosMapper pasosMapper;
    private final PasosRepository pasosRepository;

    public PasosServiceImpl(PasosMapper pasosMapper, PasosRepository pasosRepository) {
        this.pasosMapper = pasosMapper;
        this.pasosRepository = pasosRepository;
    }

    @Override
    public PasosDto crearPaso(PasosDto pasosDto) {
        Objects.requireNonNull(pasosDto, "PasosDto no puede ser null");
        Pasos pasos = pasosMapper.toEntity(pasosDto);
        return pasosMapper.toDTO(pasosRepository.save(pasos));
    }

    @Override
    public List<PasosDto> listarPasosPorReceta(Long recetaId) {
        Objects.requireNonNull(recetaId, "RecetaId no puede ser null");
        return pasosRepository.findByRecetaId(recetaId).stream()
                .map(pasosMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PasosDto obtenerPaso(Long id) {
        Pasos pasos = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));
        return pasosMapper.toDTO(pasos);
    }

    @Override
    public PasosDto actualizarPaso(Long id, PasosDto pasosDto) {
        Pasos existente = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));

        existente.setDescripcion(Objects.requireNonNull(pasosDto.descripcion(), "Descripcion no puede ser null"));
        existente.setTiempoEstimado(Objects.requireNonNull(pasosDto.tiempoEstimado(), "TiempoEstimado no puede ser null"));
        existente.setOpcional(Objects.requireNonNull(pasosDto.opcional(), "Opcional no puede ser null"));

        existente.setIngredientes(
                pasosDto.ingredientes().stream()
                        .map(ingredienteId -> {
                            Objects.requireNonNull(ingredienteId, "IngredienteId no puede ser null");
                            Ingrediente i = new Ingrediente();
                            i.setId(ingredienteId);
                            return i;
                        })
                        .collect(Collectors.toList())
        );

        return pasosMapper.toDTO(pasosRepository.save(existente));
    }

    @Override
    public void eliminarPaso(Long id) {
        Pasos pasos = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));
        pasosRepository.delete(pasos);
    }
}
