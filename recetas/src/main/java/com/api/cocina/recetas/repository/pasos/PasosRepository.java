package com.api.cocina.recetas.repository.pasos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.api.cocina.recetas.domain.Pasos;

public interface PasosRepository extends JpaRepository<Pasos, Long> {

    List<Pasos> findByRecetaId(@NonNull Long recetaId);
}
