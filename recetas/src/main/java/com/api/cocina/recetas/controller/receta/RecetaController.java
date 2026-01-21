package com.api.cocina.recetas.controller.receta;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.api.cocina.recetas.dto.ingredient.IngredienteDto;
import com.api.cocina.recetas.dto.recipe.RecetaDto;
import com.api.cocina.recetas.exceptions.RecetaNoEncontradaException;
import com.api.cocina.recetas.service.receta.RecetaService;

@RestController
@RequestMapping("/api/v1/recetas")
public class RecetaController {
    
    private final RecetaService recetaService;

    @Autowired
    public RecetaController(RecetaService recetaService){
        this.recetaService = recetaService;
    }
    
    @GetMapping("/obtenerReceta/{id}")
    public ResponseEntity<RecetaDto> obtenerReceta(@PathVariable Long id) {
        try {
            RecetaDto receta = recetaService.obtenerReceta(id);
            return ResponseEntity.ok(receta);
        } catch (RecetaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<RecetaDto>> listarRecetas() {
        List<RecetaDto> recetas = recetaService.listarRecetas();
        return ResponseEntity.ok(recetas);
    }
    
    @PostMapping
    public ResponseEntity<RecetaDto> crearReceta(@RequestBody RecetaDto receta) {
        RecetaDto creada = recetaService.crearReceta(receta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }
    
    @PutMapping("/actualizarReceta/{id}")
    public ResponseEntity<RecetaDto> actualizarReceta(@PathVariable Long id, @RequestBody RecetaDto receta) {
        try {
            RecetaDto actualizada = recetaService.actualizarReceta(id, receta);
            return ResponseEntity.ok(actualizada);
        } catch (RecetaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/eliminarReceta/{id}")
    public ResponseEntity<Void> eliminarReceta(@PathVariable Long id) {
        try {
            recetaService.eliminarReceta(id);
            return ResponseEntity.noContent().build();
        } catch (RecetaNoEncontradaException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/ingredientes")
    public ResponseEntity<List<IngredienteDto>> obtenerIngredientesDeReceta(@PathVariable Long id) {
        List<IngredienteDto> ingredientes = recetaService.obtenerIngredientesDeReceta(id);
        return ResponseEntity.ok(ingredientes);
    }

    @GetMapping("/dificultad/{dificultad}")
    public ResponseEntity<List<RecetaDto>> listarRecetasPorDificultad(@PathVariable Dificultad dificultad) {
        List<RecetaDto> recetas = recetaService.listarRecetasPorDificultad(dificultad);
        return ResponseEntity.ok(recetas);
    }

    @GetMapping("/{id}/tiempo-preparacion")
    public ResponseEntity<Integer> obtenerTiempoPreparacionDeReceta(@PathVariable Long id) {
        Integer tiempoPreparacion = recetaService.obtenerTiempoPreparacionDeReceta(id);
        return ResponseEntity.ok(tiempoPreparacion);
    }

}