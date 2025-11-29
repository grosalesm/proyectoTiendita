package modelo;

import java.math.BigDecimal;
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
public class Producto {

    private int id_producto;
    private String descripcion;
    private BigDecimal precio;
    private int stock;
    private Categoria categoria;

    public Producto(int id_producto, String descripcion, BigDecimal precio, Categoria categoria) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.precio = precio;
        this.categoria = categoria;
    }

    public Producto(int id_producto, String descripcion, Categoria categoria) {
        this.id_producto = id_producto;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

}
