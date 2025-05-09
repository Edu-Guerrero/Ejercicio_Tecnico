package com.example.demo.servicios;

import com.example.demo.entidades.Movimientos;
import com.example.demo.entidades.Cuenta;
import com.example.demo.repositorios.MovimientosRepositorio;
import com.example.demo.repositorios.CuentaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class MovimientosServicioImpl implements MovimientosServicio {
    @Autowired
    private MovimientosRepositorio movimientosRepositorio;

    @Autowired
    private CuentaRepositorio cuentaRepositorio;

    @Override
    public Movimientos registrarMovimiento(Movimientos movimiento) {
        Optional<Cuenta> cuentaOpt = cuentaRepositorio.findById(movimiento.getCuenta().getCuentaId());
        if (!cuentaOpt.isPresent()) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        Cuenta cuenta = cuentaOpt.get();

        double nuevoSaldo = cuenta.getSaldoInicial() + movimiento.getValor();

        if (nuevoSaldo < 0) {
            throw new RuntimeException("Saldo insuficiente");
        }

        cuenta.setSaldoInicial(nuevoSaldo);
        cuentaRepositorio.save(cuenta);

        movimiento.setFecha(LocalDate.now());
        movimiento.setSaldo(nuevoSaldo);
        return movimientosRepositorio.save(movimiento);
    }

    @Override
    public Movimientos actualizarMovimiento(Long id, Movimientos movimiento) {
        Movimientos act = movimientosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
        act.setTipoMovimiento(movimiento.getTipoMovimiento());
        
        return movimientosRepositorio.save(act);
    }

    @Override
    public void eliminarMovimiento(Long id) {
        movimientosRepositorio.deleteById(id);
    }

    @Override
    public List<Movimientos> listarMovimientos() {
        return movimientosRepositorio.findAll();
    }

    @Override
    public Movimientos buscarMovimientoPorId(Long id) {
        return movimientosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimiento no encontrado"));
    }
}
