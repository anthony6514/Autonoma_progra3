package com.itsqmet.ejercicio_progra.service;

import com.itsqmet.ejercicio_progra.model.ServicioExtra;
import com.itsqmet.ejercicio_progra.model.ServicioExtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ServicioExtraService {

    @Autowired
    private ServicioExtraRepository servicioExtraRepository;

    public List<ServicioExtra> obtenerTodos() {
        return servicioExtraRepository.findAll();
    }

    public Optional<ServicioExtra> obtenerPorId(Long id) {
        return servicioExtraRepository.findById(id);
    }

    public ServicioExtra crear(ServicioExtra servicioExtra) {
        return servicioExtraRepository.save(servicioExtra);
    }

    public Optional<ServicioExtra> actualizar(Long id, ServicioExtra detalles) {
        return servicioExtraRepository.findById(id).map(servicio -> {
            servicio.setNombre(detalles.getNombre());
            servicio.setCategoria(detalles.getCategoria());
            servicio.setPrecio(detalles.getPrecio());
            return servicioExtraRepository.save(servicio);
        });
    }

    public boolean eliminar(Long id) {
        if (servicioExtraRepository.existsById(id)) {
            servicioExtraRepository.deleteById(id);
            return true;
        }
        return false;
    }
}