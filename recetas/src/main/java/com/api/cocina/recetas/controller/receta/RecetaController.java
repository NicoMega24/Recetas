package com.api.cocina.recetas.controller.receta;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.cocina.recetas.domain.enums.Dificultad;
import com.api.cocina.recetas.dto.ingredient.IngredienteSimpleDto;
import com.api.cocina.recetas.dto.recipe.RecetaDto;
import com.api.cocina.recetas.dto.recipe.RecetaResumenDto;
import com.api.cocina.recetas.service.receta.RecetaService;

@RestController
@RequestMapping("/api/v1/recetas")
public class RecetaController {

    private final RecetaService recetaService;

    public RecetaController(RecetaService recetaService) {
        this.recetaService = recetaService;
    }

    @GetMapping
    public ResponseEntity<List<RecetaDto>> listar() {
        return ResponseEntity.ok(recetaService.listarRecetas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerReceta(id));
    }

    @PostMapping
    public ResponseEntity<RecetaDto> crear(@RequestBody RecetaDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(recetaService.crearReceta(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaDto> actualizar(
            @PathVariable Long id,
            @RequestBody RecetaDto dto) {
        return ResponseEntity.ok(recetaService.actualizarReceta(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        recetaService.eliminarReceta(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Endpoints de ingredientes ahora usan IngredienteSimpleDto
    @GetMapping("/{id}/ingredientes")
    public ResponseEntity<List<IngredienteSimpleDto>> ingredientes(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerIngredientesDeReceta(id));
    }

    @GetMapping("/dificultad/{dificultad}")
    public ResponseEntity<List<RecetaDto>> porDificultad(@PathVariable Dificultad dificultad) {
        return ResponseEntity.ok(recetaService.listarRecetasPorDificultad(dificultad));
    }

    @GetMapping("/{id}/tiempo-preparacion")
    public ResponseEntity<Integer> tiempoPreparacion(@PathVariable Long id) {
        return ResponseEntity.ok(recetaService.obtenerTiempoPreparacionDeReceta(id));
    }

    @GetMapping("/categoria/{categoriaId}")
    public ResponseEntity<List<RecetaResumenDto>> listarPorCategoria(@PathVariable Long categoriaId) {
        return ResponseEntity.ok(recetaService.listarRecetasPorCategoria(categoriaId));
    }

    @GetMapping("/{recetaId}/ingredientes/{pasoId}")
    public ResponseEntity<List<IngredienteSimpleDto>> ingredientesPorPaso(
            @PathVariable Long recetaId,
            @PathVariable Long pasoId) {
        return ResponseEntity.ok(recetaService.obtenerIngredientesDePaso(recetaId, pasoId));
    }
}
