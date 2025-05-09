package com.example.demo.controller;

import com.example.demo.entidades.Movimientos;
import com.example.demo.servicios.MovimientosServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movimientos")
public class MovimientosController {
    @Autowired
    private MovimientosServicio movimientosServicio;

    @PostMapping
    public ResponseEntity<?> crearMovimiento(@RequestBody Movimientos movimiento) {
        try {
            Movimientos nuevoMovimiento = movimientosServicio.registrarMovimiento(movimiento);
            return ResponseEntity.ok(nuevoMovimiento);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public List<Movimientos> listarMovimientos() {
        return movimientosServicio.listarMovimientos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movimientos> buscarMovimientoPorId(@PathVariable Long id) {
        Movimientos movimiento = movimientosServicio.buscarMovimientoPorId(id);
        return ResponseEntity.ok(movimiento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movimientos> actualizarMovimiento(@PathVariable Long id, @RequestBody Movimientos movimiento) {
        Movimientos movimientoActualizado = movimientosServicio.actualizarMovimiento(id, movimiento);
        return ResponseEntity.ok(movimientoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable Long id) {
        movimientosServicio.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
