package com.itsqmet.ejercicio_progra.controller;

import com.itsqmet.ejercicio_progra.model.Huesped;
import com.itsqmet.ejercicio_progra.service.HuespedService;
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
@RequestMapping("/api/huespedes")
public class HuespedController {

    @Autowired
    private HuespedService huespedService;

    @GetMapping
    public List<Huesped> obtenerTodos() {
        return huespedService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Huesped> obtenerPorId(@PathVariable Long id) {
        return huespedService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody Huesped huesped, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(huespedService.crear(huesped));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody Huesped huesped, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return huespedService.actualizar(id, huesped)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (huespedService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Huésped eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No encontrado"));
    }
}
