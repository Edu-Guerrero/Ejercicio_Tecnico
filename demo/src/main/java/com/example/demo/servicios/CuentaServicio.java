package com.example.demo.servicios;

import com.example.demo.entidades.Cuenta;
import java.util.List;

public interface CuentaServicio {
    Cuenta crearCuenta(Cuenta cuenta);
    Cuenta actualizarCuenta(Long id, Cuenta cuenta);
    void eliminarCuenta(Long id);
    List<Cuenta> listarCuentas();
    Cuenta buscarCuentaPorId(Long id);
}
