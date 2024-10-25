package com.api.cocina.recetas.controller.ingrediente;

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

import com.api.cocina.recetas.dto.ingredient.IngredienteDto;
import com.api.cocina.recetas.exceptions.IngredienteNoEncontradoException;
import com.api.cocina.recetas.service.ingrediente.IngredienteService;

@RestController
@RequestMapping("/api/v1/ingredientes")
public class IngredienteController {
    
    private final IngredienteService ingredienteService;
    
    @Autowired
    public IngredienteController(IngredienteService ingredienteService) {
        this.ingredienteService = ingredienteService;
    }
    
    @PostMapping
    public ResponseEntity<IngredienteDto> crearIngrediente(@RequestBody IngredienteDto ingredienteDto) {
        IngredienteDto creado = ingredienteService.crearIngrediente(ingredienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }
    
    @GetMapping
    public ResponseEntity<List<IngredienteDto>> listarIngredientes() {
        List<IngredienteDto> ingredientes = ingredienteService.listarIngredientes();
        return ResponseEntity.ok(ingredientes);
    }
    
    @GetMapping("/obtener/{id}")
    public ResponseEntity<IngredienteDto> obtenerIngrediente(@PathVariable Long id) {
        try {
            IngredienteDto ingrediente = ingredienteService.obtenerIngrediente(id);
            return ResponseEntity.ok(ingrediente);
        } catch (IngredienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<IngredienteDto> actualizarIngrediente(@PathVariable Long id, @RequestBody IngredienteDto ingredienteDto) {
        try {
            IngredienteDto actualizado = ingredienteService.actualizarIngrediente(id, ingredienteDto);
            return ResponseEntity.ok(actualizado);
        } catch (IngredienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarIngrediente(@PathVariable Long id) {
        try {
            ingredienteService.eliminarIngrediente(id);
            return ResponseEntity.noContent().build();
        } catch (IngredienteNoEncontradoException e) {
            return ResponseEntity.notFound().build();
        }
    }
}

