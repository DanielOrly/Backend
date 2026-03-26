package com.example.PruebaTecSupermercado.service;

import com.example.PruebaTecSupermercado.dto.DetaleVentaDTO;
import com.example.PruebaTecSupermercado.dto.VentaDTO;
import com.example.PruebaTecSupermercado.exception.NotFoundException;
import com.example.PruebaTecSupermercado.mapper.Mapper;
import com.example.PruebaTecSupermercado.model.DetalleVenta;
import com.example.PruebaTecSupermercado.model.Producto;
import com.example.PruebaTecSupermercado.model.Sucursal;
import com.example.PruebaTecSupermercado.model.Venta;
import com.example.PruebaTecSupermercado.repository.ProductoRepository;
import com.example.PruebaTecSupermercado.repository.SucursalRepository;
import com.example.PruebaTecSupermercado.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaService implements IVentaService{

    @Autowired
    private VentaRepository ventaRepo;
    @Autowired
    private ProductoRepository productoRepo;
    @Autowired
    private SucursalRepository sucursalRepo;



    @Override
    public List<VentaDTO> traerVentas() {
        List<Venta> ventas = ventaRepo.findAll();
        List<VentaDTO> ventasDto = new ArrayList<>();

        VentaDTO dto;
        for (Venta v : ventas) {
            dto = Mapper.toDTO(v);
            ventasDto.add (dto);
        }

        return ventasDto;
    }

    @Override
    public VentaDTO crearVenta(VentaDTO ventaDto) {
        //Validaciones
        if (ventaDto == null) throw new RuntimeException("VentaDTO es null");
        if (ventaDto.getIdSucursal() == null) throw new RuntimeException("Debe indicar la sucursal");
        if (ventaDto.getDetalle() == null || ventaDto.getDetalle().isEmpty())
            throw new RuntimeException("Debe incluir al menos un producto");

        //Buscar la sucursal
        Sucursal suc = sucursalRepo.findById(ventaDto.getIdSucursal()).orElse(null);
        if (suc == null) {
            throw new NotFoundException("Sucursal no encontrada");
        }

        //Crear la venta
        Venta vent = new Venta();
        vent.setFecha(ventaDto.getFecha());
        vent.setEstado(ventaDto.getEstado());
        vent.setSucursal(suc);
        vent.setTotal(ventaDto.getTotal());

        // La lista de detalles
        // --> Acá están los productos
        List<DetalleVenta> detalles = new ArrayList<>();
        Double totalCalculado = 0.0;

        for (DetaleVentaDTO detDTO : ventaDto.getDetalle()) {
            // Buscar producto por id (tu detDTO usa id como id de producto)
            Producto p = productoRepo.findByNombre(detDTO.getNombreProd()).orElse(null);
            if (p == null)
            {throw new RuntimeException("Producto no encontrado: " + detDTO.getNombreProd());}

            //Crear detalle
            DetalleVenta detalleVent = new DetalleVenta();
            detalleVent.setProd(p);
            detalleVent.setPrecio(detDTO.getPrecio());
            detalleVent.setCantProd(detDTO.getCantProd());
            detalleVent.setVenta(vent);

            detalles.add(detalleVent);
            totalCalculado = totalCalculado+(detDTO.getPrecio()*detDTO.getCantProd());

        }
        //Seteamos la lista de detalle Venta
        vent.setDetalle(detalles);

        //guardamos en la BD
        vent = ventaRepo.save(vent);

        //Mapeo de salida
        VentaDTO ventaSalida = Mapper.toDTO(vent);

        return ventaSalida;
    }

    @Override
    public VentaDTO actualizarVenta(Long id, VentaDTO ventaDTO) {
        //buscar si la venta existe para actualizarla
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");

        if (ventaDTO.getFecha()!=null) {
            v.setFecha(ventaDTO.getFecha());
        }
        if(ventaDTO.getEstado()!=null) {
            v.setEstado(ventaDTO.getEstado());
        }

        if (ventaDTO.getTotal()!=null) {
            v.setTotal(ventaDTO.getTotal());
        }

        if (ventaDTO.getIdSucursal()!=null) {
            Sucursal suc = sucursalRepo.findById(ventaDTO.getIdSucursal()).orElse(null);
            if (suc == null) throw new NotFoundException("Sucursal no encontrada");
            v.setSucursal(suc);
        }
        ventaRepo.save(v);

        VentaDTO ventaSalida = Mapper.toDTO(v);

        return ventaSalida;
    }

    @Override
    public void eliminarVenta(Long id) {
        Venta v = ventaRepo.findById(id).orElse(null);
        if (v == null) throw new RuntimeException("Venta no encontrada");
        ventaRepo.delete(v);

    }
}
