package com.example.PruebaTecSupermercado.service;

import com.example.PruebaTecSupermercado.dto.VentaDTO;

import java.util.List;

public interface IVentaService {


    List<VentaDTO> traerVentas();
    VentaDTO crearVenta(VentaDTO ventaDTO);
    VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO);
    void eliminarVenta(Long id);
}
