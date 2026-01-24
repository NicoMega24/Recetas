package com.api.cocina.recetas.service.categoria.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.dto.categoria.CategoriaDto;
import com.api.cocina.recetas.exceptions.ResourceNotFoundException;
import com.api.cocina.recetas.mappers.categoria.CategoriaMapper;
import com.api.cocina.recetas.repository.categoria.CategoriaRepository;
import com.api.cocina.recetas.service.categoria.CategoriaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaServiceImpl implements CategoriaService {

    private final CategoriaRepository repository;
    private final CategoriaMapper mapper;

    @Override
    public CategoriaDto crear(CategoriaDto dto) {
        Categoria categoria = mapper.toEntity(dto);
        return mapper.toDto(repository.save(categoria));
    }

    @Override
    public List<CategoriaDto> listar() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public CategoriaDto buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada"));
        return mapper.toDto(categoria);
    }

    @Override
    public void eliminar(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoría no encontrada con id" + id));

        repository.delete(categoria);
    }

}
