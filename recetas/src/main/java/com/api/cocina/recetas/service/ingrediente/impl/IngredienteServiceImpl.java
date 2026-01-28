package com.api.cocina.recetas.service.ingrediente.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.dto.ingrediente.IngredienteDto;
import com.api.cocina.recetas.exceptions.IngredienteEnUsoException;
import com.api.cocina.recetas.exceptions.IngredienteNoEncontradoException;
import com.api.cocina.recetas.mappers.ingrediente.IngredienteMapper;
import com.api.cocina.recetas.repository.ingrediente.IngredienteRepository;
import com.api.cocina.recetas.service.ingrediente.IngredienteService;

@Service
public class IngredienteServiceImpl implements IngredienteService {

    private final IngredienteRepository ingredienteRepository;
    private final IngredienteMapper ingredienteMapper;

    public IngredienteServiceImpl(IngredienteRepository ingredienteRepository,
                                  IngredienteMapper ingredienteMapper) {
        this.ingredienteRepository = ingredienteRepository;
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public IngredienteDto obtenerIngrediente(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNoEncontradoException(id));
        return ingredienteMapper.toDto(ingrediente);
    }

    @Override
    public List<IngredienteDto> listarIngredientes() {
        return ingredienteRepository.findAll().stream()
                .map(ingredienteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public IngredienteDto crearIngrediente(IngredienteDto dto) {
        Objects.requireNonNull(dto, "IngredienteDto no puede ser null");
        Ingrediente ingrediente = ingredienteMapper.toEntity(dto);
        return ingredienteMapper.toDto(ingredienteRepository.save(ingrediente));
    }

    @Override
    public IngredienteDto actualizarIngrediente(Long id, IngredienteDto dto) {
        Ingrediente existente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNoEncontradoException(id));

        existente.setNombre(Objects.requireNonNull(dto.nombre(), "Nombre no puede ser null"));
        existente.setDescripcion(Objects.requireNonNull(dto.descripcion(), "Descripcion no puede ser null"));

        Ingrediente actualizado = ingredienteRepository.save(existente);
        return ingredienteMapper.toDto(actualizado);
    }

    @Override
    public void eliminarIngrediente(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id)
                .orElseThrow(() -> new IngredienteNoEncontradoException(id));

        if (!ingrediente.getPasos().isEmpty()) {
            throw new IngredienteEnUsoException(id);
        }

        ingredienteRepository.delete(ingrediente);
    }

}
