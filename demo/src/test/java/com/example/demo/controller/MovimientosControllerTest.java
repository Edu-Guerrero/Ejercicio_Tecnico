package com.example.demo.controller;

import com.example.demo.entidades.Cuenta;
import com.example.demo.entidades.Movimientos;
import com.example.demo.servicios.MovimientosServicio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(MovimientosController.class)
public class MovimientosControllerTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MovimientosServicio movimientosServicio;

    @Autowired
    private ObjectMapper objectMapper;
    private Movimientos movimiento;

    @BeforeEach
    public void setUp() {
        Cuenta cuenta = new Cuenta();
        cuenta.setCuentaId(1L);
        cuenta.setSaldoInicial(1000.0);

        movimiento = new Movimientos();
        movimiento.setMovimientoId(1L);
        movimiento.setTipoMovimiento("RETIRO");
        movimiento.setValor(-100.0);
        movimiento.setSaldo(900.0);
        movimiento.setFecha(LocalDate.now());
        movimiento.setCuenta(cuenta);
    }

    @Test
    public void testCrearMovimiento() throws Exception {
        Mockito.when(movimientosServicio.registrarMovimiento(any(Movimientos.class))).thenReturn(movimiento);

        mockMvc.perform(post("/movimientos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(movimiento)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.cuenta.cuentaId").value(1L))
                .andExpect(jsonPath("$.tipoMovimiento").value("RETIRO"))
                .andExpect(jsonPath("$.valor").value(-100.0))
                .andExpect(jsonPath("$.saldo").value(900.0));
    }

    @Test
    public void testListarMovimientos() throws Exception {
        Mockito.when(movimientosServicio.listarMovimientos())
                .thenReturn(Arrays.asList(movimiento));

        mockMvc.perform(get("/movimientos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].tipoMovimiento").value("RETIRO"));
    }
}
