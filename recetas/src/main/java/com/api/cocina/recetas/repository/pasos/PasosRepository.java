package com.api.cocina.recetas.repository.pasos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.api.cocina.recetas.domain.Pasos;

public interface PasosRepository extends JpaRepository<Pasos, Long> {

    List<Pasos> findByRecetaId(Long recetaId);
}
