package com.itsqmet.ejercicio_progra.service;

import com.itsqmet.ejercicio_progra.model.Huesped;
import com.itsqmet.ejercicio_progra.model.HuespedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HuespedService {

    @Autowired
    private HuespedRepository huespedRepository;

    public List<Huesped> obtenerTodos() {
        return huespedRepository.findAll();
    }

    public Optional<Huesped> obtenerPorId(Long id) {
        return huespedRepository.findById(id);
    }

    public Huesped crear(Huesped huesped) {
        return huespedRepository.save(huesped);
    }

    public Optional<Huesped> actualizar(Long id, Huesped detalles) {
        return huespedRepository.findById(id).map(huesped -> {
            huesped.setNombre(detalles.getNombre());
            huesped.setDocumento(detalles.getDocumento());
            huesped.setEmail(detalles.getEmail());
            huesped.setTelefono(detalles.getTelefono());
            return huespedRepository.save(huesped);
        });
    }

    public boolean eliminar(Long id) {
        if (huespedRepository.existsById(id)) {
            huespedRepository.deleteById(id);
            return true;
        }
        return false;
    }
}