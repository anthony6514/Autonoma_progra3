package com.itsqmet.ejercicio_progra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "servicios_extras")
public class ServicioExtra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del servicio es obligatorio")
    private String nombre;

    @NotBlank(message = "La categoría del servicio es obligatoria")
    private String categoria;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    public ServicioExtra() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
}