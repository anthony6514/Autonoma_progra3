package com.itsqmet.ejercicio_progra.service;

import com.itsqmet.ejercicio_progra.model.Habitacion;
import com.itsqmet.ejercicio_progra.model.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HabitacionService {

    @Autowired
    private HabitacionRepository habitacionRepository;

    public List<Habitacion> obtenerTodos() {
        return habitacionRepository.findAll();
    }

    public Optional<Habitacion> obtenerPorId(Long id) {
        return habitacionRepository.findById(id);
    }

    public Habitacion crear(Habitacion habitacion) {
        return habitacionRepository.save(habitacion);
    }

    public Optional<Habitacion> actualizar(Long id, Habitacion detalles) {
        return habitacionRepository.findById(id).map(habitacion -> {
            habitacion.setNumero(detalles.getNumero());
            habitacion.setTipo(detalles.getTipo());
            habitacion.setPrecio(detalles.getPrecio());
            habitacion.setDescripcion(detalles.getDescripcion());
            return habitacionRepository.save(habitacion);
        });
    }

    public boolean eliminar(Long id) {
        if (habitacionRepository.existsById(id)) {
            habitacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}