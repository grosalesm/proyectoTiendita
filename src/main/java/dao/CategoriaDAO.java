package dao;

import java.util.List;
import modelo.Categoria;

public interface CategoriaDAO {
    public List<Categoria> listarCategorias();
    public Categoria agregarCategoria(Categoria c);
    public Categoria buscarCategoriaPorNombre(String nombre);
    public Categoria buscarCategoriaPorID(int id);
    public boolean eliminarCategoriaPorId(int id);
    public boolean actualizarCategoria(int id, String nombre);
}