package com.example.demo.repositorios;

import com.example.demo.entidades.Movimientos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MovimientosRepositorio extends JpaRepository<Movimientos, Long> {
    Movimientos findByCuenta_NumeroCuenta(String numeroCuenta);    
}
