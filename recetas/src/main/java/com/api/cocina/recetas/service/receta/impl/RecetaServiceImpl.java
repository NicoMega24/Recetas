package com.api.cocina.recetas.service.receta.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.dto.recipe.RecetaDto;
import com.api.cocina.recetas.exceptions.RecetaNoEncontradaException;
import com.api.cocina.recetas.mappers.receta.RecetaMapper;
import com.api.cocina.recetas.repository.receta.RecetaRepository;
import com.api.cocina.recetas.service.receta.RecetaService;


@Service
public class RecetaServiceImpl implements RecetaService {
    
    private final RecetaMapper recetaMapper;
    private final RecetaRepository recetaRepository;

    @Autowired
    public RecetaServiceImpl(RecetaMapper recetaMapper, RecetaRepository recetaRepository){
        this.recetaMapper = recetaMapper;
        this.recetaRepository = recetaRepository;
    }
    
    @Override
    public RecetaDto obtenerReceta(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));
        return recetaMapper.toDTO(receta);
    }
    
    @Override
    public List<RecetaDto> listarRecetas() {
        List<Receta> recetas = recetaRepository.findAll();
        return recetas.stream().map(recetaMapper::toDTO).collect(Collectors.toList());
    }
    
    @Override
    public RecetaDto crearReceta(RecetaDto recetaDto) {
        Receta receta = recetaMapper.toEntity(recetaDto);
        return recetaMapper.toDTO(recetaRepository.save(receta));
    }
    
    @Override
    public RecetaDto actualizarReceta(Long id, RecetaDto receta) throws RecetaNoEncontradaException {
        Receta existente = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));
        existente.setNombre(receta.nombre());
        existente.setDescripcion(receta.descripcion());
        existente.setDificultad(receta.dificultad());
        Categoria categoria = new Categoria();
        categoria.setId(receta.categoria());
        Receta actualizada = recetaRepository.save(existente);
        return recetaMapper.toDTO(actualizada);
    }
    
    @Override
    public void eliminarReceta(Long id) throws RecetaNoEncontradaException {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));
        recetaRepository.delete(receta);
    }
}
