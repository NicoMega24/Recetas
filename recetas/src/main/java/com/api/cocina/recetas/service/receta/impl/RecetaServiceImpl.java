package com.api.cocina.recetas.service.receta.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Ingrediente;
import com.api.cocina.recetas.domain.Pasos;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingrediente.IngredienteSimpleDto;
import com.api.cocina.recetas.dto.receta.RecetaDto;
import com.api.cocina.recetas.dto.receta.RecetaResumenDto;
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

    public RecetaServiceImpl(RecetaMapper recetaMapper, RecetaRepository recetaRepository, IngredienteMapper ingredienteMapper) {
        this.recetaMapper = recetaMapper;
        this.recetaRepository = recetaRepository;
        this.ingredienteMapper = ingredienteMapper;
    }

    @Override
    public RecetaDto obtenerReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));
        return recetaMapper.toDTO(receta);
    }

    @Override
    public List<RecetaDto> listarRecetas() {
        return recetaRepository.findAll().stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RecetaDto crearReceta(RecetaDto recetaDto) {
        Objects.requireNonNull(recetaDto, "RecetaDto no puede ser null");
        Receta receta = recetaMapper.toEntity(recetaDto);
        return recetaMapper.toDTO(recetaRepository.save(receta));
    }

    @Override
    public RecetaDto actualizarReceta(Long id, RecetaDto recetadto) {
        Receta existente = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        existente.setNombre(Objects.requireNonNull(recetadto.nombre(), "Nombre no puede ser null"));
        existente.setDescripcion(Objects.requireNonNull(recetadto.descripcion(), "Descripcion no puede ser null"));
        existente.setDificultad(Objects.requireNonNull(recetadto.dificultad(), "Dificultad no puede ser null"));

        Categoria categoria = new Categoria();
        categoria.setId(Objects.requireNonNull(recetadto.categoria(), "Categoria no puede ser null"));
        existente.setCategoria(categoria);

        Receta actualizada = recetaRepository.save(existente);
        return recetaMapper.toDTO(actualizada);
    }

    @Override
    public void eliminarReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        receta.getPasos().forEach(paso -> {
            List<Ingrediente> ingredientesASacar = paso.getIngredientes().stream()
                    .filter(ingrediente -> ingrediente.getPasos().size() == 1)
                    .toList();
            paso.getIngredientes().removeAll(ingredientesASacar);
        });

        recetaRepository.delete(receta);
    }

    @Override
    public List<IngredienteSimpleDto> obtenerIngredientesDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        return receta.getPasos().stream()
                .flatMap(paso -> Objects.requireNonNull(paso.getIngredientes()).stream())
                .map(ingredienteMapper::toSimpleDTO) // <-- usamos toSimpleDTO
                .distinct()
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaDto> listarRecetasPorDificultad(Dificultad dificultad) {
        Objects.requireNonNull(dificultad, "Dificultad no puede ser null");
        return recetaRepository.findByDificultad(dificultad).stream()
                .map(recetaMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RecetaResumenDto> listarRecetasPorCategoria(Long categoriaId) {
        List<Receta> recetas = recetaRepository.findByCategoriaId(categoriaId);
        return recetas.stream()
                .map(receta -> {
                    int tiempoTotal = receta.getPasos().stream()
                            .filter(p -> !Boolean.TRUE.equals(p.getOpcional()))
                            .mapToInt(Pasos::getTiempoEstimado)
                            .sum();
                    return new RecetaResumenDto(
                            receta.getId(),
                            receta.getNombre(),
                            receta.getDescripcion(),
                            receta.getDificultad(),
                            tiempoTotal
                    );
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<IngredienteSimpleDto> obtenerIngredientesDePaso(Long recetaId, Long pasoId) {
        Receta receta = recetaRepository.findById(recetaId)
                .orElseThrow(() -> new RecetaNoEncontradaException(recetaId));

        return receta.getPasos().stream()
                .filter(p -> p.getId().equals(pasoId))
                .flatMap(p -> p.getIngredientes().stream())
                .map(ingredienteMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Integer obtenerTiempoPreparacionDeReceta(Long id) {
        Receta receta = recetaRepository.findById(id)
                .orElseThrow(() -> new RecetaNoEncontradaException(id));

        return receta.getPasos().stream()
                .mapToInt(Pasos::getTiempoEstimado)
                .sum();
    }
}
