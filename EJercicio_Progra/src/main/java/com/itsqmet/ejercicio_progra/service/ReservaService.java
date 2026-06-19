package com.itsqmet.ejercicio_progra.service;

import com.itsqmet.ejercicio_progra.model.Reserva;
import com.itsqmet.ejercicio_progra.model.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    public List<Reserva> obtenerTodos() {
        return reservaRepository.findAll();
    }

    public Optional<Reserva> obtenerPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public Reserva crear(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> actualizar(Long id, Reserva detalles) {
        return reservaRepository.findById(id).map(reserva -> {
            reserva.setCodigo(detalles.getCodigo());
            reserva.setFechaEntrada(detalles.getFechaEntrada());
            reserva.setFechaSalida(detalles.getFechaSalida());
            reserva.setEstado(detalles.getEstado());
            return reservaRepository.save(reserva);
        });
    }

    public boolean eliminar(Long id) {
        if (reservaRepository.existsById(id)) {
            reservaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}