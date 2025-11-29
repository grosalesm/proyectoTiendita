package modelo;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Venta {
    private int id_venta;
    private Cliente cliente;
    private Producto producto;
    private int cantidad;
    private LocalDate  fecha;
}