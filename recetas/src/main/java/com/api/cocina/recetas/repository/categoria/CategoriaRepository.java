package com.api.cocina.recetas.repository.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cocina.recetas.domain.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
