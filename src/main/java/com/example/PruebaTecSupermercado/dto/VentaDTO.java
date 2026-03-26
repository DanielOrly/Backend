package com.example.PruebaTecSupermercado.dto;

import com.example.PruebaTecSupermercado.model.DetalleVenta;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VentaDTO {
    //Los DTO son para transferir datos

    //datos de la venta
    private Long id;
    private LocalDate fecha;
    private String estado;
    //datos de la sucursal
    private Long idSucursal;
    //lista de detalles
    private List<DetaleVentaDTO> detalle;

    //total de la venta
    private Double total;
}
