package com.api.cocina.recetas.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pasos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String descripcion;

    private Integer tiempoEstimado;

    private Boolean opcional;

    @ManyToOne
    @JoinColumn(name = "receta_id")
    private Receta receta;

    @ManyToMany
    @JoinTable(
        name = "pasos_ingrediente",
        joinColumns = @JoinColumn(name = "pasos_id"),
        inverseJoinColumns = @JoinColumn(name = "ingrediente_id")
    )
    private List<Ingrediente> ingredientes = new ArrayList<>();


    public Pasos(String descripcion, Integer tiempoEstimado, Boolean opcional, Receta receta) {
        this.descripcion = descripcion;
        this.tiempoEstimado = tiempoEstimado;
        this.opcional = opcional;
        this.receta = receta;
    }
}