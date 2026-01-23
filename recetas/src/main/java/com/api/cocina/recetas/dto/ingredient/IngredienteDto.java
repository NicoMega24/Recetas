package com.api.cocina.recetas.dto.ingredient;

import jakarta.validation.constraints.NotBlank;

public record IngredienteDto(

    Long id,

    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotBlank(message = "La descripción es obligatoria")
    String descripcion
) {}
