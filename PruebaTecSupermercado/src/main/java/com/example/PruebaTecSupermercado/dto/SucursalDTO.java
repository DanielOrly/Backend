package com.example.PruebaTecSupermercado.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SucursalDTO {
    //Para transferir datos. No van a la base de datos solo para dar datos
    private Long id;
    private String nombre;
    private String direccion;


}
