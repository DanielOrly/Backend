package com.example.PruebaTecSupermercado.service;

import com.example.PruebaTecSupermercado.dto.ProductoDTO;

import java.util.List;

public interface IProductoService {

    List<ProductoDTO> traerProductos();
    ProductoDTO crearProducto(ProductoDTO productoDTO);
    ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto);
    void eliminarProducto(Long id);

}
