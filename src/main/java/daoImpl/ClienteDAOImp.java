package daoImpl;

import conexion.Conexion;
import dao.ClienteDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.Cliente;

public class ClienteDAOImp implements ClienteDAO {

    private static final String COL_ID = "ID_CLIENTE";
    private static final String COL_NOMBRES = "NOMBRES";
    private static final String COL_TELEFONO = "TELEFONO";
    private static final String COL_CORREO = "CORREO";

    @Override
    public int agregarCliente(Cliente c) {
        String sql = "INSERT INTO CLIENTE (NOMBRES, TELEFONO, CORREO) VALUES (?,?,?)";
        int nuevoId = 0;
        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCorreo());

            int filas = ps.executeUpdate();

            if (filas == 1) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        nuevoId = rs.getInt(1);
                        c.setId_cliente(nuevoId);
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al agregarCliente: " + e.getMessage());
        }
        return nuevoId;
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        String sql = "SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Cliente.builder()
                            .id_cliente(rs.getInt(COL_ID))
                            .nombres(rs.getString(COL_NOMBRES))
                            .telefono(rs.getString(COL_TELEFONO))
                            .correo(rs.getString(COL_CORREO))
                            .build();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al buscarClientePorId: " + e.getMessage());
        }
        return null;
    }

    //actualizarCliente
    @Override
    public boolean actualizarCliente(Cliente c) {
        String sql = """
                     UPDATE CLIENTE
                     SET
                     NOMBRES = ?, TELEFONO = ?, CORREO = ?
                     WHERE ID_CLIENTE = ?
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, c.getNombres());
            ps.setString(2, c.getTelefono());
            ps.setString(3, c.getCorreo());
            ps.setInt(4, c.getId_cliente());

            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al actualizarCliente: " + e.getMessage());
        }
        return false;
    }

    //eliminarCliente
    @Override
    public boolean eliminarClientePorId(int id) {
        String sql = "DELETE FROM CLIENTE WHERE ID_CLIENTE = ?";

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setInt(1, id);

            int fila = ps.executeUpdate();

            return fila > 0;

        } catch (SQLException e) {
            System.out.println("Error al eliminarClientePorId: " + e.getMessage());
        }

        return false;
    }

    //listarClientes
    @Override
    public List<Cliente> listarClientes() {
        ArrayList<Cliente> lista = new ArrayList<>();
        String sql = """
                     SELECT
                     ID_CLIENTE, NOMBRES, TELEFONO, CORREO
                     FROM CLIENTE;
                     """;

        try (Connection cn = Conexion.getConnection(); PreparedStatement ps = cn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente c = Cliente.builder()
                        .id_cliente(rs.getInt(COL_ID))
                        .nombres(rs.getString(COL_NOMBRES))
                        .telefono(rs.getString(COL_TELEFONO))
                        .correo(rs.getString(COL_CORREO))
                        .build();

                lista.add(c);
            }

        } catch (SQLException e) {
            System.out.println("Error al listarClientes: " + e.getMessage());
        }
        return lista;
    }
}
