package daoImpl;

import conexion.Conexion;
import dao.ProductoDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Categoria;
import modelo.Producto;

public class ProductoDAOImp implements ProductoDAO {
    private static final String COL_ID = "ID_CAT"; 
    private static final String COL_CATE = "NOM_CAT";
    private static final String COL_ID_PROD = "ID_PROD";
    private static final String COL_DES_PROD = "DES_PROD";
    private static final String COL_PRECIO = "PRECIO";


    //1. Lista todos los productos con el nombre categoria (JOIN)
    @Override
    public List<Producto> listarProducto() {
        List<Producto> lista = new ArrayList<>();
        String sql = """
                SELECT 
                    P.ID_PRODUCTO AS ID_PROD, 
                    P.DESCRIPCION AS DES_PROD,
                    P.PRECIO,
                    C.ID_CATEGORIA AS ID_CAT,
                    C.NOMBRE AS NOM_CAT,
                    P.STOCK
                FROM PRODUCTO AS P INNER JOIN CATEGORIA AS C
                ON P.ID_CATEGORIA = C.ID_CATEGORIA""";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Categoria categoria = Categoria.builder()
                        .id_categoria(rs.getInt("ID_CAT"))
                        .nombre(rs.getString("NOM_CAT"))
                        .build();

                Producto prod = Producto.builder()
                        .id_producto(rs.getInt("ID_PROD"))
                        .descripcion(rs.getString("DES_PROD"))
                        .precio(rs.getBigDecimal("PRECIO"))
                        .categoria(categoria)
                        .stock(rs.getInt("STOCK"))
                        .build();

                lista.add(prod);
            }
        } catch (SQLException e) {
            System.out.println("Error al ListarProducto: " + e.getMessage());
        }

        return lista;
    }

    //2. Buscar Producto por ID
    @Override
    public Producto buscarProductoPorId(int id) {
        Producto prod = null;
        String sql = """
                SELECT P.ID_PRODUCTO AS ID_PROD, 
                P.DESCRIPCION AS DES_PROD,
                P.PRECIO,
                C.ID_CATEGORIA AS ID_CAT,
                C.NOMBRE AS NOM_CAT
                FROM PRODUCTO AS P INNER JOIN CATEGORIA AS C
                ON P.ID_CATEGORIA = C.ID_CATEGORIA
                WHERE P.ID_PRODUCTO = ?""";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Categoria categoria = Categoria.builder()
                            .id_categoria(rs.getInt(COL_ID))
                            .nombre(rs.getString(COL_CATE))
                            .build();

                    prod = Producto.builder()
                            .id_producto(rs.getInt(COL_ID_PROD))
                            .descripcion(rs.getString(COL_DES_PROD))
                            .precio(rs.getBigDecimal(COL_PRECIO))
                            .categoria(categoria)
                            .build();

                    return prod;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarProductoPorId: " + e.getMessage());
        }
        return prod;
    }

    //3.Listar productos por categoría
    @Override
    public List<Producto> listarProductoPorCategoria(String cat) {
        List<Producto> lista = new ArrayList<>();
        String sql = """
                     SELECT
                     P.ID_PRODUCTO AS ID_PROD,
                     P.DESCRIPCION AS PRODUCTO,
                     P.PRECIO,
                     C.ID_CATEGORIA AS ID_CAT,
                     C.NOMBRE AS CATEGORIA
                     FROM PRODUCTO AS P
                     INNER JOIN CATEGORIA AS C
                     ON P.ID_CATEGORIA = C.ID_CATEGORIA
                     WHERE C.NOMBRE = ?
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, cat);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Categoria categoria = Categoria.builder()
                            .id_categoria(rs.getInt(COL_ID))
                            .nombre(rs.getString(COL_CATE))
                            .build();

                    Producto prod = Producto.builder()
                            .id_producto(rs.getInt(COL_ID_PROD))
                            .descripcion(rs.getString(COL_DES_PROD))
                            .precio(rs.getBigDecimal(COL_PRECIO))
                            .categoria(categoria)
                            .build();

                    lista.add(prod);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listarProductoPorCategoria: " + e.getMessage());
        }

        return lista;
    }

    //4.Insertar producto (retornar id)
    @Override
    public int agregarProducto(Producto p) {
        int nuevoId = 0;
        String sql = """
                     INSERT INTO PRODUCTO(DESCRIPCION, PRECIO, STOCK, ID_CATEGORIA)
                     VALUES (?,?,?,?)
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getDescripcion());
            ps.setBigDecimal(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getCategoria().getId_categoria());

            int filas = ps.executeUpdate();

            if (filas == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        nuevoId = rs.getInt(1);
                        p.setId_producto(nuevoId);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al agregarProducto: " + e.getMessage());
        }
        return nuevoId;
    }

    //5.Actualizar producto
    @Override
    public boolean actualizarProducto(Producto p) {
        String sql = """
                     UPDATE PRODUCTO
                     SET 
                     DESCRIPCION = ?,
                     PRECIO = ?,
                     STOCK = ?,
                     ID_CATEGORIA = ?
                     WHERE ID_PRODUCTO = ?
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, p.getDescripcion());
            ps.setBigDecimal(2, p.getPrecio());
            ps.setInt(3, p.getStock());
            ps.setInt(4, p.getCategoria().getId_categoria());
            ps.setInt(5, p.getId_producto());

            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizarProducto: " + e.getMessage());
        }
        return false;
    }

    //6.Eliminar producto
    @Override
    public boolean eliminarProductoPorId(int id) {
        String sql = "DELETE FROM PRODUCTO WHERE ID_PRODUCTO = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminarProductoPorId: " + e.getMessage());
        }

        return false;
    }
}
