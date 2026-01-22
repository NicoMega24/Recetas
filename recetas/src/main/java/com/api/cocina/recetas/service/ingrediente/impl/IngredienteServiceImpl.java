package com.api.cocina.recetas.service.ingrediente.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;
import com.api.cocina.recetas.exceptions.IngredienteNoEncontradoException;
import com.api.cocina.recetas.mappers.ingrediente.IngredienteMapper;
import com.api.cocina.recetas.repository.ingrediente.IngredienteRepository;
import com.api.cocina.recetas.service.ingrediente.IngredienteService;

@Service
public class IngredienteServiceImpl implements IngredienteService {
    
    private final IngredienteMapper ingredienteMapper;
    private final IngredienteRepository ingredienteRepository;
    
    @Autowired
    public IngredienteServiceImpl(IngredienteMapper ingredienteMapper, IngredienteRepository ingredienteRepository) {
        this.ingredienteMapper = ingredienteMapper;
        this.ingredienteRepository = ingredienteRepository;
    }
    
    @Override
    public IngredienteDto crearIngrediente(IngredienteDto ingredienteDto) {
        Ingrediente ingrediente = ingredienteMapper.toEntity(ingredienteDto);
        return ingredienteMapper.toDTO(ingredienteRepository.save(ingrediente));
    }
    
    @Override
    public List<IngredienteDto> listarIngredientes() {
        List<Ingrediente> ingredientes = ingredienteRepository.findAll();
        return ingredientes.stream().map(ingredienteMapper::toDTO).collect(Collectors.toList());
    }
    
    @Override
    public IngredienteDto obtenerIngrediente(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new IngredienteNoEncontradoException(id));
        return ingredienteMapper.toDTO(ingrediente);
    }
    
    @Override
    public IngredienteDto actualizarIngrediente(Long id, IngredienteDto ingredienteDto) {
        Ingrediente existente = ingredienteRepository.findById(id).orElseThrow(() -> new IngredienteNoEncontradoException(id));
        existente.setNombre(ingredienteDto.nombre());
        existente.setDescripcion(ingredienteDto.descripcion());
        Ingrediente actualizado = ingredienteRepository.save(existente);
        return ingredienteMapper.toDTO(actualizado);
    }
    
    @Override
    public void eliminarIngrediente(Long id) {
        Ingrediente ingrediente = ingredienteRepository.findById(id).orElseThrow(() -> new IngredienteNoEncontradoException(id));
        ingredienteRepository.delete(ingrediente);
    }
}