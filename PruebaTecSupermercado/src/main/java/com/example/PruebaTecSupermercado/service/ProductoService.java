package com.example.PruebaTecSupermercado.service;

import com.example.PruebaTecSupermercado.dto.ProductoDTO;
import com.example.PruebaTecSupermercado.exception.NotFoundException;
import com.example.PruebaTecSupermercado.mapper.Mapper;
import com.example.PruebaTecSupermercado.model.Producto;
import com.example.PruebaTecSupermercado.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repo;

    @Override
    public List<ProductoDTO> traerProductos() {

        return repo.findAll().stream().map(Mapper::toDTO).toList();
    }

    @Override
    public ProductoDTO crearProducto(ProductoDTO productoDTO) {
        Producto prod = Producto.builder()
                .nombre(productoDTO.getNombre())
                .categoria(productoDTO.getCategoria())
                .precio(productoDTO.getPrecio())
                .cantidad(productoDTO.getCantidad())
                .build();

        return  Mapper.toDTO(repo.save(prod));
    }

    @Override
    public ProductoDTO actualizarProducto(Long id, ProductoDTO productoDto) {
        //vamos a buscar si existe ese producto
        Producto prod = repo.findById(id)
        .orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        prod.setNombre(productoDto.getNombre());
        prod.setCategoria(productoDto.getCategoria());
        prod.setCantidad(productoDto.getCantidad());
        prod.setPrecio(productoDto.getPrecio());

        return Mapper.toDTO(repo.save(prod));
            }

    @Override
    public void eliminarProducto(Long id) {

        if (!repo.existsById(id)){
            throw new NotFoundException("Producto no encontrado para eliminar");
        }
        repo.deleteById(id);

    }
}
