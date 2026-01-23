package com.api.cocina.recetas.controller.pasos;

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

import com.api.cocina.recetas.dto.pasos.PasosDto;
import com.api.cocina.recetas.service.pasos.PasosService;

@RestController
@RequestMapping("/api/v1/pasos")
public class PasosController {

    private final PasosService pasosService;

    public PasosController(PasosService pasosService) {
        this.pasosService = pasosService;
    }

    @PostMapping
    public ResponseEntity<PasosDto> crear(@RequestBody PasosDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pasosService.crearPaso(dto));
    }

    @GetMapping("/receta/{recetaId}")
    public ResponseEntity<List<PasosDto>> listarPorReceta(@PathVariable Long recetaId) {
        return ResponseEntity.ok(pasosService.listarPasosPorReceta(recetaId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PasosDto> obtener(@PathVariable Long id) {
        return ResponseEntity.ok(pasosService.obtenerPaso(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PasosDto> actualizar(@PathVariable Long id, @RequestBody PasosDto dto) {
        return ResponseEntity.ok(pasosService.actualizarPaso(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pasosService.eliminarPaso(id);
        return ResponseEntity.noContent().build();
    }
}
