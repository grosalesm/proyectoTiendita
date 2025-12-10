package daoImpl;

import conexion.Conexion;
import dao.VentaDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import modelo.Venta;
import modelo.modeloDTO.VentaListadoDTO;

public class VentaDAOImp implements VentaDAO {

    @Override
    public int registrarVenta(Venta v) {
        int nuevoId = 0;
        String sql = """
                 INSERT INTO VENTA(ID_CLIENTE, ID_PRODUCTO, CANTIDAD, FECHA)
                 VALUES(?, ?, ?, ?)
                 """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, v.getCliente().getId_cliente());
            ps.setInt(2, v.getProducto().getId_producto());
            ps.setInt(3, v.getCantidad());
            ps.setDate(4, java.sql.Date.valueOf(v.getFecha()));

            int fila = ps.executeUpdate();

            if (fila == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        nuevoId = rs.getInt(1);
                        v.setId_venta(nuevoId);
                    }
                }
            }

        } catch (SQLException e) {
            System.out.println("Error al registrarVenta: " + e.getMessage());
        }

        return nuevoId;
    }

    //Listar Ventas
    @Override
    public List<VentaListadoDTO> listarVentas() {
        ArrayList<VentaListadoDTO> lista = new ArrayList<>();
        String sql = """
                     SELECT
                     V.ID_VENTA AS IDVEN,
                     C.ID_CLIENTE AS CODCLI,
                     C.NOMBRES AS CLIENTE,
                     P.DESCRIPCION AS PRODUCTO,
                     V.CANTIDAD,
                     P.PRECIO,
                     (P.PRECIO * V.CANTIDAD) AS TOTAL,
                     V.FECHA
                     FROM VENTA AS V
                     INNER JOIN CLIENTE AS C ON V.ID_CLIENTE = C.ID_CLIENTE
                     INNER JOIN PRODUCTO AS P ON V.ID_PRODUCTO = P.ID_PRODUCTO
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                VentaListadoDTO dto = VentaListadoDTO.builder()
                        .idVenta(rs.getInt("IDVEN"))
                        .idCliente(rs.getInt("CODCLI"))
                        .cliente(rs.getString("CLIENTE"))
                        .producto(rs.getString("PRODUCTO"))
                        .cantidad(rs.getInt("CANTIDAD"))
                        .precioUnit(rs.getBigDecimal("PRECIO"))
                        .total(rs.getBigDecimal("TOTAL"))
                        .fecha(rs.getDate("FECHA").toLocalDate())
                        .build();

                lista.add(dto);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarVentas: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<VentaListadoDTO> buscarVentaPorCliente(String nombre) {
        ArrayList<VentaListadoDTO> lista = new ArrayList<>();
        String sql = """
                     SELECT 
                     V.ID_VENTA,
                     C.ID_CLIENTE,
                     C.NOMBRES,
                     P.DESCRIPCION,
                     V.CANTIDAD,
                     P.PRECIO,
                     (P.PRECIO * V.CANTIDAD) AS TOTAL,
                     V.FECHA
                     FROM VENTA V
                     INNER JOIN CLIENTE C ON V.ID_CLIENTE = C.ID_CLIENTE
                     INNER JOIN PRODUCTO P ON V.ID_PRODUCTO = P.ID_PRODUCTO
                     WHERE C.NOMBRES LIKE CONCAT('%', ?, '%')
                     """;
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VentaListadoDTO venta = VentaListadoDTO.builder()
                            .idVenta(rs.getInt("ID_VENTA"))
                            .idCliente(rs.getInt("ID_CLIENTE"))
                            .cliente(rs.getString("NOMBRES"))
                            .producto(rs.getString("DESCRIPCION"))
                            .cantidad(rs.getInt("CANTIDAD"))
                            .precioUnit(rs.getBigDecimal("PRECIO"))
                            .total(rs.getBigDecimal("TOTAL"))
                            .fecha(rs.getDate("FECHA").toLocalDate())
                            .build();

                    lista.add(venta);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarVentaPorCliente: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public List<VentaListadoDTO> buscarVentaPorFecha(String fecha) {
        ArrayList<VentaListadoDTO> lista = new ArrayList<>();
        String sql = """
                     SELECT 
                     V.ID_VENTA,
                     C.ID_CLIENTE,
                     C.NOMBRES,
                     P.DESCRIPCION,
                     V.CANTIDAD,
                     P.PRECIO,
                     (P.PRECIO * V.CANTIDAD) AS TOTAL,
                     V.FECHA
                     FROM VENTA V
                     INNER JOIN CLIENTE C ON V.ID_CLIENTE = C.ID_CLIENTE
                     INNER JOIN PRODUCTO P ON V.ID_PRODUCTO = P.ID_PRODUCTO
                     WHERE V.FECHA = ?
                     """;
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setString(1, fecha);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    VentaListadoDTO venta = VentaListadoDTO.builder()
                            .idVenta(rs.getInt("ID_VENTA"))
                            .idCliente(rs.getInt("ID_CLIENTE"))
                            .cliente(rs.getString("NOMBRES"))
                            .producto(rs.getString("DESCRIPCION"))
                            .cantidad(rs.getInt("CANTIDAD"))
                            .precioUnit(rs.getBigDecimal("PRECIO"))
                            .total(rs.getBigDecimal("TOTAL"))
                            .fecha(rs.getDate("FECHA").toLocalDate())
                            .build();

                    lista.add(venta);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarVentaPorFecha: " + e.getMessage());
        }
        return lista;
    }

    @Override
    public boolean eliminarVenta(int idVen) {
        String sql = "DELETE FROM VENTA WHERE ID_VENTA = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {
            ps.setInt(1, idVen);

            int filas = ps.executeUpdate();

            return filas > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminarVenta: " + e.getMessage());
        }

        return false;
    }

    @Override
    public VentaListadoDTO buscarVentaPorId(int id) {
        String sql = """
                SELECT
                V.ID_VENTA,
                C.ID_CLIENTE,
                C.NOMBRES,
                P.DESCRIPCION,
                V.CANTIDAD,
                P.PRECIO,
                (P.PRECIO * V.CANTIDAD) AS TOTAL,
                V.FECHA
                FROM VENTA V
                INNER JOIN CLIENTE C ON V.ID_CLIENTE = C.ID_CLIENTE
                INNER JOIN PRODUCTO P ON V.ID_PRODUCTO = P.ID_PRODUCTO
                WHERE V.ID_VENTA = ?
                """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return VentaListadoDTO.builder()
                            .idVenta(rs.getInt("ID_VENTA"))
                            .idCliente(rs.getInt("ID_CLIENTE"))
                            .cliente(rs.getString("NOMBRES"))
                            .producto(rs.getString("DESCRIPCION"))
                            .cantidad(rs.getInt("CANTIDAD"))
                            .precioUnit(rs.getBigDecimal("PRECIO"))
                            .total(rs.getBigDecimal("TOTAL"))
                            .fecha(rs.getDate("FECHA").toLocalDate())
                            .build();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarVentaPorId: " + e.getMessage());
        }
        return null;
    }
}
