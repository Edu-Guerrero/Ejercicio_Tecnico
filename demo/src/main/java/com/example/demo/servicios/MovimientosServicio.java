package com.example.demo.servicios;

import com.example.demo.entidades.Movimientos;

import java.util.List;

public interface MovimientosServicio {
    Movimientos registrarMovimiento(Movimientos movimiento);
    Movimientos actualizarMovimiento(Long id, Movimientos movimiento);
    void eliminarMovimiento(Long id);
    List<Movimientos> listarMovimientos();
    Movimientos buscarMovimientoPorId(Long id);
}
