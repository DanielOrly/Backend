package com.example.PruebaTecSupermercado.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor //Constructor vacio
@AllArgsConstructor //Constructor por parametro
@Builder
@Entity
public class Sucursal {
    //Mapeo para la persistencia en la base de datos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String direccion;


}
