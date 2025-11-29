package dao;

import java.util.List;
import modelo.Producto;

public interface ProductoDAO {
    public List<Producto> listarProducto();
    public Producto buscarProductoPorId(int id);
    public List<Producto> listarProductoPorCategoria(String cat);
    public int agregarProducto(Producto p);
    public boolean actualizarProducto(Producto p);
    public boolean eliminarProductoPorId(int id);
}