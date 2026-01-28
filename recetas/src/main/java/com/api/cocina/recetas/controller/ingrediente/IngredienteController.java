package com.api.cocina.recetas.controller.ingrediente;

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

import com.api.cocina.recetas.dto.ingrediente.IngredienteDto;
import com.api.cocina.recetas.service.ingrediente.IngredienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/ingredientes")
public class IngredienteController {

    private final IngredienteService ingredienteService;

    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }

    @GetMapping
    public ResponseEntity<List<IngredienteDto>> listar() {
        return ResponseEntity.ok(ingredienteService.listarIngredientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IngredienteDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(ingredienteService.obtenerIngrediente(id));
    }

    @PostMapping
    public ResponseEntity<IngredienteDto> crear(@Valid@RequestBody IngredienteDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ingredienteService.crearIngrediente(dto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<IngredienteDto> actualizar(@PathVariable Long id,
                                                     @RequestBody IngredienteDto dto) {
        return ResponseEntity.ok(ingredienteService.actualizarIngrediente(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        ingredienteService.eliminarIngrediente(id);
        return ResponseEntity.noContent().build();
    }
}
