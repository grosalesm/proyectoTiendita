package dao;

import modelo.Cliente;

public interface ClienteDAO {
    
    public int agregarCliente(Cliente c);
    public Cliente buscarClientePorId(int id);
    public boolean actualizarCliente(Cliente c);
    public boolean eliminarClientePorId(int id);
}