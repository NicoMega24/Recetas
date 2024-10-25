package com.api.cocina.recetas.repository.receta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cocina.recetas.domain.Categoria;
import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.domain.enums.Dificultad;

public interface RecetaRepository extends JpaRepository<Receta, Long> {
    List<Receta> findByCategoria(Categoria categoria);

    List<Receta> findByDificultad(Dificultad dificultad);
    
}