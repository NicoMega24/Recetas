package com.api.cocina.recetas.dto.steps;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record PasosDto(

        Long id,

        @NotBlank(message = "La descripción es obligatoria")
        String descripcion,

        @NotNull(message = "El tiempo estimado es obligatorio")
        Integer tiempoEstimado,

        @NotNull(message = "El paso opcional no puede ser nulo")
        Boolean opcional,

        @NotNull(message = "La receta es obligatoria")
        Long receta,   // ✅ correcto

        @NotEmpty(message = "Debe tener al menos un ingrediente")
        List<Long> ingredientes // ✅ correcto
) {}
