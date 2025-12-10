package dao;

import java.util.List;
import modelo.Venta;
import modelo.modeloDTO.VentaListadoDTO;

public interface VentaDAO {
    public int registrarVenta(Venta v);
    public List<VentaListadoDTO> listarVentas();
    public List<VentaListadoDTO> buscarVentaPorCliente(String nombre);
    public List<VentaListadoDTO> buscarVentaPorFecha(String fecha);
    public boolean eliminarVenta(int idVen);
    public VentaListadoDTO buscarVentaPorId(int id);
}