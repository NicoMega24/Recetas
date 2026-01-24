package com.api.cocina.recetas.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDto {

    private Long id;

    @NotBlank(message = "El nombre de la categoría es obligatorio")
    String nombre;
}
