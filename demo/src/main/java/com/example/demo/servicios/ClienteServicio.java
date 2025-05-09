package com.example.demo.servicios;

import com.example.demo.entidades.Cliente;
import java.util.List;

public interface ClienteServicio {
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Long id, Cliente cliente);
    void eliminarCliente(Long id);
    List<Cliente> listarClientes();
    Cliente buscarClientePorId(Long id);
}
