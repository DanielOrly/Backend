package com.example.PruebaTecSupermercado.service;

import com.example.PruebaTecSupermercado.dto.SucursalDTO;
import com.example.PruebaTecSupermercado.model.Sucursal;

import java.util.List;

public interface ISucursalService {

    //Metodos abstractos
    List<SucursalDTO> traerSucursales();
    SucursalDTO crearSucursal(SucursalDTO sucursalDto);
    SucursalDTO actualizarSucursal(Long id, SucursalDTO sucursalDto);
    void eliminarSucursal(Long id);

}
