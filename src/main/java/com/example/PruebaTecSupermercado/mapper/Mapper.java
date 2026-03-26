package com.example.PruebaTecSupermercado.mapper;

import com.example.PruebaTecSupermercado.dto.DetaleVentaDTO;
import com.example.PruebaTecSupermercado.dto.ProductoDTO;
import com.example.PruebaTecSupermercado.dto.SucursalDTO;
import com.example.PruebaTecSupermercado.dto.VentaDTO;
import com.example.PruebaTecSupermercado.model.DetalleVenta;
import com.example.PruebaTecSupermercado.model.Producto;
import com.example.PruebaTecSupermercado.model.Sucursal;
import com.example.PruebaTecSupermercado.model.Venta;

import java.util.stream.Collectors;

public class Mapper {


    //Mapeo de Producto a ProductoDTO
    //Metodo estatico
    public static ProductoDTO toDTO(Producto p) {
        if (p == null) return null;

        return  ProductoDTO.builder()
                .id(p.getId())
                .nombre(p.getNombre())
                .categoria(p.getCategoria())
                .precio(p.getPrecio())
                .build();
    }

    //Mapeo de Venta a VentaDTO
    public static VentaDTO toDTO(Venta venta) {
        if (venta == null) return null;

        var detalle = venta.getDetalle().stream().map(det ->
                DetaleVentaDTO.builder()
                    .id(det.getProd().getId())
                    .nombreProd(det.getProd().getNombre())
                    .cantProd(det.getCantProd())
                    .precio(det.getPrecio())
                    .subtotal(det.getPrecio() * det.getCantProd())
                    .build()
                ).collect(Collectors.toList());

        var total = detalle.stream()
                .map(DetaleVentaDTO::getSubtotal)
                .reduce(0.0, Double::sum);

        return VentaDTO.builder()
                .id(venta.getId())
                .fecha(venta.getFecha())
                .idSucursal(venta.getSucursal().getId())
                .estado(venta.getEstado())
                .detalle(detalle)
                .total(total)
                .build();
    }


    //Mapeo de Sucursal a SucursalDTO
    public static SucursalDTO toDTO(Sucursal s){
        if (s == null) return null;

        return SucursalDTO.builder()
                .id(s.getId())
                .nombre(s.getNombre())
                .direccion(s.getDireccion())
                .build();
    }


}
