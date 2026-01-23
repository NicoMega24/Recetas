package com.api.cocina.recetas.repository.receta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.api.cocina.recetas.domain.Receta;
import com.api.cocina.recetas.domain.enums.Dificultad;

public interface RecetaRepository extends JpaRepository<Receta, Long> {

    List<Receta> findByCategoriaId(@NonNull Long categoriaId);

    List<Receta> findByDificultad(@NonNull Dificultad dificultad);
}
