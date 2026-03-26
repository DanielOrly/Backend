package com.example.PruebaTecSupermercado.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //Cada renglon de la factura
    //Venta
    @ManyToOne
    private Venta venta;

    //Producto
    @ManyToOne
    private Producto prod;
    private Integer cantProd;
    private Double precio;

}
