package com.api.cocina.recetas.service.receta.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingrediente.IngredienteSimpleDto;
import com.api.cocina.recetas.dto.receta.RecetaDto;
import com.api.cocina.recetas.dto.receta.RecetaResumenDto;
import com.api.cocina.recetas.exceptions.RecetaNoEncontradaException;
import com.api.cocina.recetas.exceptions.ResourceNotFoundException;
import com.api.cocina.recetas.mappers.ingrediente.IngredienteMapper;
import com.api.cocina.recetas.mappers.receta.RecetaMapper;
import com.api.cocina.recetas.repository.categoria.CategoriaRepository;
import com.api.cocina.recetas.repository.receta.RecetaRepository;
import com.api.cocina.recetas.service.receta.RecetaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RecetaServiceImpl implements RecetaService {

    private final RecetaRepository recetaRepository;
    private final CategoriaRepository categoriaRepository;
    private final RecetaMapper recetaMapper;
    private final IngredienteMapper ingredienteMapper;

    @Override
    public RecetaDto obtenerReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        return recetaMapper.toDto(receta);
    }

    @Override
    public List<RecetaDto> listarRecetas() {
        return recetaRepository.findAll()
                .stream()
                .map(recetaMapper::toDto)
                .toList();
    }

    @Override
    public RecetaDto crearReceta(RecetaDto recetaDto) {
        Objects.requireNonNull(recetaDto, "La receta no puede ser null");

        Long categoriaId = Objects.requireNonNull(
                recetaDto.categoriaId(),
                "La categoría es obligatoria"
        );

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada con id " + categoriaId)
                );

        Receta receta = new Receta();
        receta.setNombre(recetaDto.nombre());
        receta.setDescripcion(recetaDto.descripcion());
        receta.setDificultad(recetaDto.dificultad());
        receta.setCategoria(categoria);

        Receta guardada = recetaRepository.save(receta);
        return recetaMapper.toDto(guardada);
    }

    @Override
    public RecetaDto actualizarReceta(Long id, RecetaDto recetaDto) {
        Receta existente = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        existente.setNombre(
                Objects.requireNonNull(recetaDto.nombre(), "El nombre es obligatorio")
        );
        existente.setDescripcion(
                Objects.requireNonNull(recetaDto.descripcion(), "La descripción es obligatoria")
        );
        existente.setDificultad(
                Objects.requireNonNull(recetaDto.dificultad(), "La dificultad es obligatoria")
        );

        Long categoriaId = Objects.requireNonNull(
                recetaDto.categoriaId(),
                "La categoría es obligatoria"
        );

        Categoria categoria = categoriaRepository.findById(categoriaId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Categoría no encontrada con id " + categoriaId)
                );

        existente.setCategoria(categoria);

        return recetaMapper.toDto(recetaRepository.save(existente));
    }

    @Override
    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        recetaRepository.delete(receta);
    }

    @Override
    public List<IngredienteSimpleDto> obtenerIngredientesDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        return receta.getPasos()
                .stream()
                .flatMap(paso -> paso.getIngredientes().stream())
                .map(ingredienteMapper::toSimpleDto)
                .distinct()
                .toList();
    }

    @Override
    public List<RecetaDto> listarRecetasPorDificultad(Dificultad dificultad) {
        Objects.requireNonNull(dificultad, "La dificultad no puede ser null");

        return recetaRepository.findByDificultad(dificultad)
                .stream()
                .map(recetaMapper::toDto)
                .toList();
    }

    @Override
    public List<RecetaResumenDto> listarRecetasPorCategoria(Long categoriaId) {
        return recetaRepository.findByCategoriaId(categoriaId)
                .stream()
                .map(receta -> {
                    int tiempoTotal = receta.getPasos()
                            .stream()
                            .filter(p -> !Boolean.TRUE.equals(p.getOpcional()))
                            .mapToInt(Pasos::getTiempoEstimado)
                            .sum();

                    return recetaMapper.toResumenDto(receta, tiempoTotal);
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredienteSimpleDto> obtenerIngredientesDePaso(Long recetaId, Long pasoId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RecetaNoEncontradaException(recetaId));

        return receta.getPasos()
                .stream()
                .filter(p -> p.getId().equals(pasoId))
                .flatMap(p -> p.getIngredientes().stream())
                .map(ingredienteMapper::toSimpleDto)
                .toList();
    }

    @Override
    public Integer obtenerTiempoPreparacionDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        return receta.getPasos()
                .stream()
                .mapToInt(Pasos::getTiempoEstimado)
                .sum();
    }
}
