package com.itsqmet.ejercicio_progra.controller;

import com.itsqmet.ejercicio_progra.model.ServicioExtra;
import com.itsqmet.ejercicio_progra.service.ServicioExtraService;
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
@RequestMapping("/api/servicios-extras")
public class ServicioExtraController {

    @Autowired
    private ServicioExtraService servicioExtraService;

    @GetMapping
    public List<ServicioExtra> obtenerTodos() {
        return servicioExtraService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServicioExtra> obtenerPorId(@PathVariable Long id) {
        return servicioExtraService.obtenerPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@Valid @RequestBody ServicioExtra servicio, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(servicioExtraService.crear(servicio));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @Valid @RequestBody ServicioExtra servicio, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));
            return ResponseEntity.badRequest().body(errores);
        }
        return servicioExtraService.actualizar(id, servicio)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (servicioExtraService.eliminar(id)) {
            return ResponseEntity.ok(Map.of("mensaje", "Servicio eliminado"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("error", "No encontrado"));
    }
}
