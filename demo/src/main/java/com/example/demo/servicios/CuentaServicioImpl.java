package com.example.demo.servicios;

import com.example.demo.entidades.Cuenta;
import com.example.demo.repositorios.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CuentaServicioImpl implements CuentaServicio {

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @Override
    public Cuenta crearCuenta(Cuenta cuenta) {
        return cuentaRepositorio.save(cuenta);
    }

    @Override
    public Cuenta actualizarCuenta(Long id, Cuenta cuenta) {
        Cuenta act = cuentaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
        act.setNumeroCuenta(cuenta.getNumeroCuenta());
        act.setSaldoInicial(cuenta.getSaldoInicial());
        act.setEstado(cuenta.getEstado());
        return cuentaRepositorio.save(act);
    }

    @Override
    public void eliminarCuenta(Long id) {
        cuentaRepositorio.deleteById(id);
    }

    @Override
    public List<Cuenta> listarCuentas() {
        return cuentaRepositorio.findAll();
    }

    @Override
    public Cuenta buscarCuentaPorId(Long id) {
        return cuentaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }
    
}
