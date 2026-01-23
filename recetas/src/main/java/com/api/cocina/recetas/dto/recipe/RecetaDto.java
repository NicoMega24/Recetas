package com.api.cocina.recetas.dto.recipe;

import com.api.cocina.recetas.domain.enums.Dificultad;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RecetaDto(

    Long id,

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,
    
    @Size(max = 5000, message = "La descripción no puede superar los 5000 caracteres")
    @NotBlank(message = "La descripción es obligatoria")
    String descripcion,

    @NotNull(message = "La dificultad es obligatoria")
    Dificultad dificultad,

    @NotNull(message = "La categoría es obligatoria")
    Long categoria

) {}
