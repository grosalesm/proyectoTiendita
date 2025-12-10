package daoImpl;

import conexion.Conexion;
import dao.CategoriaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import modelo.Categoria;

public class CategoriaDAOImp implements CategoriaDAO {

    private static final String COL_ID = "ID_CATEGORIA";
    private static final String COL_NOMBRE = "NOMBRE";

    //1.Listar todas las categorías
    @Override
    public List<Categoria> listarCategorias() {
        List<Categoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM CATEGORIA";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria cat = Categoria.builder()
                        .id_categoria(rs.getInt(COL_ID))
                        .nombre(rs.getString(COL_NOMBRE))
                        .build();
                lista.add(cat);
            }
        } catch (SQLException e) {
            System.out.println("Error al listarCategoria: " + e.getMessage());
        }

        return lista;
    }

    //2.Buscar categoría por id
    @Override
    public Categoria buscarCategoriaPorNombre(String nombre) {
        Categoria cat = null;
        String sql = "SELECT ID_CATEGORIA, NOMBRE FROM CATEGORIA WHERE ID_CATEGORIA = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cat = Categoria.builder()
                            .id_categoria(rs.getInt(COL_ID))
                            .nombre(rs.getString(COL_NOMBRE))
                            .build();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarCategoriaPorNombre: " + e.getMessage());
        }

        return cat;
    }

    @Override
    public Categoria buscarCategoriaPorID(int id) {
        Categoria cat = null;
        String sql = "SELECT ID_CATEGORIA, NOMBRE FROM CATEGORIA WHERE ID_CATEGORIA = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cat = Categoria.builder()
                            .id_categoria(rs.getInt(COL_ID))
                            .nombre(rs.getString(COL_NOMBRE))
                            .build();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarCategoriaPorNombre: " + e.getMessage());
        }

        return cat;
    }

    //3.Agregar categoría (retornar el id generado)
    @Override
    public Categoria agregarCategoria(Categoria c) {
        String sql = "INSERT INTO CATEGORIA (NOMBRE) VALUES (?)";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombre());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    c.setId_categoria(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregarCategoria: " + e.getMessage());
        }

        return c;
    }

    //4.Actualizar categoría
    @Override
    public boolean actualizarCategoria(int id, String nombre) {
        String sql = "UPDATE CATEGORIA SET NOMBRE = ? WHERE ID_CATEGORIA = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setInt(2, id);

            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizarCategoria: " + e.getMessage());
        }

        return false;
    }

    //5.Eliminar categoría por id
    @Override
    public boolean eliminarCategoriaPorId(int id) {
        String sql = "DELETE FROM CATEGORIA WHERE ID_CATEGORIA = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminarCategoriaPorId: " + e.getMessage());
        }

        return false;
    }

}
