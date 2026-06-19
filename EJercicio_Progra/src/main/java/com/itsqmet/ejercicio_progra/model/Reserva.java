package com.itsqmet.ejercicio_progra.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El código de reserva es obligatorio")
    private String codigo;

    @NotNull(message = "La fecha de entrada es obligatoria")
    private LocalDate fechaEntrada;

    @NotNull(message = "La fecha de salida es obligatoria")
    private LocalDate fechaSalida;

    @NotBlank(message = "El estado es obligatorio")
    private String estado;

    public Reserva() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }
    public LocalDate getFechaEntrada() { return fechaEntrada; }
    public void setFechaEntrada(LocalDate fechaEntrada) { this.fechaEntrada = fechaEntrada; }
    public LocalDate getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDate fechaSalida) { this.fechaSalida = fechaSalida; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
}