package com.api.cocina.recetas.service.receta.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;
import com.api.cocina.recetas.dto.recipe.RecetaDto;
import com.api.cocina.recetas.exceptions.RecetaNoEncontradaException;
import com.api.cocina.recetas.mappers.ingrediente.IngredienteMapper;
import com.api.cocina.recetas.mappers.receta.RecetaMapper;
import com.api.cocina.recetas.repository.receta.RecetaRepository;
import com.api.cocina.recetas.service.receta.RecetaService;


@Service
public class RecetaServiceImpl implements RecetaService {
    
    private final RecetaMapper recetaMapper;
    private final RecetaRepository recetaRepository;
    private final IngredienteMapper ingredienteMapper;

    @Autowired
    public RecetaServiceImpl(RecetaMapper recetaMapper, RecetaRepository recetaRepository, IngredienteMapper ingredienteMapper){
        this.recetaMapper = recetaMapper;
        this.recetaRepository = recetaRepository;
        this.ingredienteMapper = ingredienteMapper;
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
    public RecetaDto actualizarReceta(Long id, RecetaDto recetadto) {
        Receta existente = recetaRepository.findById(id)
            .orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));

        existente.setNombre(recetadto.nombre());
        existente.setDescripcion(recetadto.descripcion());
        existente.setDificultad(recetadto.dificultad());

        Categoria categoria = new Categoria();
        categoria.setId(recetadto.categoria());
        existente.setCategoria(categoria);

        Receta actualizada = recetaRepository.save(existente);
        return recetaMapper.toDTO(actualizada);
    }

    
    @Override
    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));
        recetaRepository.delete(receta);
    }

    @Override
    public List<IngredienteDto> obtenerIngredientesDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));

        List<IngredienteDto> ingredientes = new ArrayList<>();
        receta.getPasos().forEach(paso -> {
            paso.getIngredientes().forEach(ingrediente -> {
                ingredientes.add(ingredienteMapper.toDTO(ingrediente));
            });
        });
        return receta.getPasos().stream()
            .flatMap(paso -> paso.getIngredientes().stream())
            .map(ingredienteMapper::toDTO)
            .distinct()
            .collect(Collectors.toList());
            
    }

    @Override
    public List<RecetaDto> listarRecetasPorDificultad(Dificultad dificultad) {
        List<Receta> recetas = recetaRepository.findByDificultad(dificultad);
        return recetas.stream().map(recetaMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Integer obtenerTiempoPreparacionDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id).orElseThrow(() -> new RecetaNoEncontradaException("Receta no encontrada"));

        return receta.getPasos().stream().mapToInt(Pasos::getTiempoEstimado).sum();
    }
}
