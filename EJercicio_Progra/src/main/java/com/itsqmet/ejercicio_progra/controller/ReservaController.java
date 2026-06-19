package com.itsqmet.ejercicio_progra.controller;

import com.itsqmet.ejercicio_progra.model.Reserva;
import com.itsqmet.ejercicio_progra.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/reservas")
public class ReservaController {

    @Autowired
    private ReservaService reservaService;

    @GetMapping
    public List<Reserva> obtenerTodos() {
        return reservaService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reserva> obtenerPorId(@PathVariable Long id) {
        return reservaService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Reserva reserva, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.crear(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Reserva reserva, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return reservaService.actualizar(id, reserva)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (reservaService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Reserva eliminada"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No encontrada"));
    }
}
