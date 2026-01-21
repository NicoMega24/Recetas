package com.api.cocina.recetas.service.pasos.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    public PasosServiceImpl(PasosMapper pasosMapper, PasosRepository pasosRepository) {
        this.pasosMapper = pasosMapper;
        this.pasosRepository = pasosRepository;
    }
    
    @Override
    public PasosDto crearPaso(PasosDto pasosDto) {
        Pasos pasos = pasosMapper.toEntity(pasosDto);
        return pasosMapper.toDTO(pasosRepository.save(pasos));
    }
    
    @Override
    public List<PasosDto> listarPasosPorReceta(Long recetaId) {
        List<Pasos> pasos = pasosRepository.findByRecetaId(recetaId);
        return pasos.stream()
            .map(pasosMapper::toDTO)
            .collect(Collectors.toList());
    }

    
    @Override
    public PasosDto obtenerPaso(Long id) {
        Pasos pasos = pasosRepository.findById(id).orElseThrow(() -> new PasoNoEncontradoException("Paso no encontrado"));
        return pasosMapper.toDTO(pasos);
    }
    
    @Override
    public PasosDto actualizarPaso(Long id, PasosDto pasosDto) {
        Pasos existente = pasosRepository.findById(id).orElseThrow(() -> new PasoNoEncontradoException("Paso no encontrado"));
        existente.setDescripcion(pasosDto.descripcion());
        existente.setTiempoEstimado(pasosDto.tiempoEstimado());
        existente.setOpcional(pasosDto.opcional());
        
        // Actualizar ingredientes
        existente.setIngredientes(pasosDto.ingredientes().stream()
            .map(ingredienteId -> {
                Ingrediente ingrediente = new Ingrediente();
                ingrediente.setId(ingredienteId);
                return ingrediente;
            })
            .collect(Collectors.toList()));
        
        Pasos actualizado = pasosRepository.save(existente);
        return pasosMapper.toDTO(actualizado);
    }
    
    @Override
    public void eliminarPaso(Long id) {
        Pasos pasos = pasosRepository.findById(id).orElseThrow(() -> new PasoNoEncontradoException("Paso no encontrado"));
        pasosRepository.delete(pasos);
    }
}
