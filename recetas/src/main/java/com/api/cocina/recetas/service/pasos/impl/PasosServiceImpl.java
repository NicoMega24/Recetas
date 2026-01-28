package com.api.cocina.recetas.service.pasos.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.pasos.PasosDto;
import com.api.cocina.recetas.exceptions.PasoNoEncontradoException;
import com.api.cocina.recetas.exceptions.ResourceNotFoundException;
import com.api.cocina.recetas.mappers.pasos.PasosMapper;
import com.api.cocina.recetas.repository.ingrediente.IngredienteRepository;
import com.api.cocina.recetas.repository.pasos.PasosRepository;
import com.api.cocina.recetas.repository.receta.RecetaRepository;
import com.api.cocina.recetas.service.pasos.PasosService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PasosServiceImpl implements PasosService {

    private final PasosRepository pasosRepository;
    private final RecetaRepository recetaRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PasosMapper pasosMapper;

    @Override
    public PasosDto crearPaso(PasosDto pasosDto) {
        Objects.requireNonNull(pasosDto, "PasosDto no puede ser null");

        Receta receta = recetaRepository.findById(pasosDto.receta())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Receta no encontrada con id " + pasosDto.receta()
                        )
                );

        List<Ingrediente> ingredientes = ingredienteRepository.findAllById(
                pasosDto.ingredientes()
        );

        if (ingredientes.size() != pasosDto.ingredientes().size()) {
            throw new ResourceNotFoundException("Uno o más ingredientes no existen");
        }

        Pasos paso = new Pasos();
        paso.setDescripcion(pasosDto.descripcion());
        paso.setTiempoEstimado(pasosDto.tiempoEstimado());
        paso.setOpcional(pasosDto.opcional());
        paso.setReceta(receta);
        paso.setIngredientes(ingredientes);

        return pasosMapper.toDto(pasosRepository.save(paso));
    }

    @Override
    public List<PasosDto> listarPasosPorReceta(Long recetaId) {
        Objects.requireNonNull(recetaId, "recetaId no puede ser null");

        return pasosRepository.findByRecetaId(recetaId)
                .stream()
                .map(pasosMapper::toDto)
                .toList();
    }

    @Override
    public PasosDto obtenerPaso(Long id) {
        Pasos paso = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));

        return pasosMapper.toDto(paso);
    }

    @Override
    public PasosDto actualizarPaso(Long id, PasosDto pasosDto) {
        Pasos existente = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));

        existente.setDescripcion(
                Objects.requireNonNull(pasosDto.descripcion(), "La descripción es obligatoria")
        );
        existente.setTiempoEstimado(
                Objects.requireNonNull(pasosDto.tiempoEstimado(), "El tiempo estimado es obligatorio")
        );
        existente.setOpcional(
                Objects.requireNonNull(pasosDto.opcional(), "El campo opcional es obligatorio")
        );

        List<Ingrediente> ingredientes = ingredienteRepository.findAllById(
                pasosDto.ingredientes()
        );

        if (ingredientes.size() != pasosDto.ingredientes().size()) {
            throw new ResourceNotFoundException("Uno o más ingredientes no existen");
        }

        existente.setIngredientes(ingredientes);

        return pasosMapper.toDto(pasosRepository.save(existente));
    }

    @Override
    public void eliminarPaso(Long id) {
        Pasos paso = pasosRepository.findById(id)
                .orElseThrow(() -> new PasoNoEncontradoException(id));

        pasosRepository.delete(paso);
    }
}
