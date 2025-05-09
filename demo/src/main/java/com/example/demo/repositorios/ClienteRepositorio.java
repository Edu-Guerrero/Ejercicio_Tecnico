package com.example.demo.repositorios;

import com.example.demo.entidades.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
    Cliente findByIdentificacion(String identificacion);    
}
