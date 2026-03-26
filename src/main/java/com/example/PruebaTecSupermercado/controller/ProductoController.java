package com.example.PruebaTecSupermercado.controller;


import com.example.PruebaTecSupermercado.dto.ProductoDTO;
import com.example.PruebaTecSupermercado.model.Producto;
import com.example.PruebaTecSupermercado.service.IProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private IProductoService productoService;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> traerProducto() {

        return ResponseEntity.ok(productoService.traerProductos());
    }

    @PostMapping
    public ResponseEntity<ProductoDTO> crearProducto(@RequestBody ProductoDTO dto){
        ProductoDTO creado = productoService.crearProducto(dto);

        return ResponseEntity.created(URI.create("/api/productos" + creado.getId())).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> actualizarProducto (@PathVariable Long id,
                                                           @RequestBody ProductoDTO dto){
        return ResponseEntity.ok(productoService.actualizarProducto(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrarProducto (@PathVariable Long id) {
        productoService.eliminarProducto(id);

        return ResponseEntity.noContent().build();
    }


}



