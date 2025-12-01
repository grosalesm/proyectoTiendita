package modelo.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class VentaListadoDTO {

    private int idVenta;          // CODCLI
    private int idCliente;          // CODCLI
    private String cliente;         // Nombres del cliente
    private String producto;        // Nombre del producto
    private int cantidad;           // Cantidad vendida
    private BigDecimal precioUnit;  // Precio unitario
    private BigDecimal total;       // Precio * Cantidad
    private LocalDate fecha;        // Fecha de la venta
}
