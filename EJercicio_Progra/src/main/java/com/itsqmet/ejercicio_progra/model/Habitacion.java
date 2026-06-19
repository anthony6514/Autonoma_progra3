package com.itsqmet.ejercicio_progra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El número de habitación es obligatorio")
    private String numero;

    @NotBlank(message = "El tipo de habitación es obligatorio")
    private String tipo;

    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    @NotBlank(message = "La descripción es obligatoria")
    private String descripcion;

    public Habitacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNumero() { return numero; }
    public void setNumero(String numero) { this.numero = numero; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public Double getPrecio() { return precio; }
    public void setPrecio(Double precio) { this.precio = precio; }
    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}