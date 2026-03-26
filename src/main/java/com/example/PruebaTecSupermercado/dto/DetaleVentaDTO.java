package com.example.PruebaTecSupermercado.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetaleVentaDTO {

    private Long id;
    private String nombreProd;
    private Integer cantProd;
    private Double precio;
    private Double subtotal;
}
