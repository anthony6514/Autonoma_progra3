package com.itsqmet.ejercicio_progra.controller;

import com.itsqmet.ejercicio_progra.model.Habitacion;
import com.itsqmet.ejercicio_progra.service.HabitacionService;
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
@RequestMapping("/api/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService habitacionService;

    @GetMapping
    public List<Habitacion> obtenerTodos() {
        return habitacionService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Habitacion> obtenerPorId(@PathVariable Long id) {
        return habitacionService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Habitacion habitacion, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(habitacionService.crear(habitacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Habitacion habitacion, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return habitacionService.actualizar(id, habitacion)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (habitacionService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Habitación eliminada"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No encontrada"));
    }
}
