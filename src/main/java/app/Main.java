package app;

import dao.CategoriaDAO;
import dao.ClienteDAO;
import dao.ProductoDAO;
import dao.VentaDAO;
import daoImpl.CategoriaDAOImp;
import daoImpl.ClienteDAOImp;
import daoImpl.ProductoDAOImp;
import daoImpl.VentaDAOImp;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelo.Categoria;
import modelo.Cliente;
import modelo.Producto;
import modelo.Venta;
import modelo.modeloDTO.VentaListadoDTO;

public class Main {

    private static final CategoriaDAO categoriaDAO = new CategoriaDAOImp();
    private static final ProductoDAO productoDAO = new ProductoDAOImp();
    private static final ClienteDAO clienteDAO = new ClienteDAOImp();
    private static final VentaDAO ventaDAO = new VentaDAOImp();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;

        do {
            mostrarMenu();
            System.out.print("Seleccione una opcion: ");
            opcion = leerEntero(sc);

            switch (opcion) {
                case 1 ->
                    menuCategorias(sc);
                case 2 ->
                    menuProductos(sc);
                case 3 ->
                    menuClientes(sc);
                case 4 ->
                    menuVentas(sc);
                case 0 ->
                    System.out.println("Saliendo del sistema...");
                default ->
                    System.out.println("Opcion invalida.");
            }

        } while (opcion != 0);

        sc.close();
    }

    // MENÚ PRINCIPAL
    private static void mostrarMenu() {
        System.out.println("\n=== MENU PRINCIPAL ===");
        System.out.println("1. Gestionar Categorias");
        System.out.println("2. Gestionar Productos");
        System.out.println("3. Gestionar Clientes");
        System.out.println("4. Gestionar Ventas");
        System.out.println("0. Salir");
    }

    // -------------------------------------------------------
    // SUBMENÚ: CATEGORÍAS
    private static void menuCategorias(Scanner sc) {
        int op;

        do {
            System.out.println("\n--- Gestion de Categorias ---");
            System.out.println("1. Listar categorias");
            System.out.println("2. Agregar categoria");
            System.out.println("3. Buscar categoria por ID");
            System.out.println("4. Actualizar categoria");
            System.out.println("5. Eliminar categoria");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            op = leerEntero(sc);

            switch (op) {
                case 1 ->
                    listarCategorias();
                case 2 ->
                    agregarCategoria(sc);
                case 3 ->
                    buscarCategoria(sc);
                case 4 ->
                    actualizarCategoria(sc);
                case 5 ->
                    eliminarCategoria(sc);
                case 0 ->
                    System.out.println("Volviendo...");
                default ->
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);
    }

    // SUBMENÚ: PRODUCTOS
    private static void menuProductos(Scanner sc) {
        int op;

        do {
            System.out.println("\n--- Gestion de Productos ---");
            System.out.println("1. Listar productos");
            System.out.println("2. Agregar producto");
            System.out.println("3. Buscar producto por ID");
            System.out.println("4. Listar productos por categoria");
            System.out.println("5. Actualizar producto");
            System.out.println("6. Eliminar producto");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            op = leerEntero(sc);

            switch (op) {
                case 1 ->
                    listarProductos();
                case 2 ->
                    agregarProducto(sc);
                case 3 ->
                    buscarProducto(sc);
                case 4 ->
                    listarProductosPorCategoria(sc);
                case 5 ->
                    actualizarProducto(sc);
                case 6 ->
                    eliminarProducto(sc);
                case 0 ->
                    System.out.println("Volviendo...");
                default ->
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);
    }

    // SUBMENÚ: CLIENTES
    private static void menuClientes(Scanner sc) {
        int op;

        do {
            System.out.println("\n--- Gestion de Clientes ---");
            System.out.println("1. Listar clientes");
            System.out.println("2. Agregar cliente");
            System.out.println("3. Buscar cliente por ID");
            System.out.println("4. Actualizar cliente");
            System.out.println("5. Eliminar cliente");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            op = leerEntero(sc);

            switch (op) {
                case 1 ->
                    listarClientes();
                case 2 ->
                    agregarCliente(sc);
                case 3 ->
                    buscarCliente(sc);
                case 4 ->
                    actualizarCliente(sc);
                case 5 ->
                    eliminarCliente(sc);
                case 0 ->
                    System.out.println("Volviendo...");
                default ->
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);
    }

    // SUBMENÚ: VENTAS
    private static void menuVentas(Scanner sc) {
        int op;

        do {
            System.out.println("\n--- Gestion de Ventas ---");
            System.out.println("1. Registrar venta");
            System.out.println("2. Listar ventas");
            System.out.println("3. Buscar ventas por cliente");
            System.out.println("4. Buscar ventas por fecha");
            System.out.println("5. Eliminar venta");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            op = leerEntero(sc);

            switch (op) {
                case 1 ->
                    registrarVenta(sc);
                case 2 ->
                    listarVentas();
                case 3 ->
                    buscarVentaPorCliente(sc);
                case 4 ->
                    buscarVentaPorFecha(sc);
                case 5 ->
                    eliminarVenta(sc);
                case 0 ->
                    System.out.println("Volviendo...");
                default ->
                    System.out.println("Opcion invalida.");
            }

        } while (op != 0);
    }

    // -------------------------------------------------------
    // Métodos auxiliares para lectura
    private static int leerEntero(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Ingrese un numero valido: ");
            sc.next();
        }
        return sc.nextInt();
    }

    // -------------------------------------------------------
    // Aquí van los métodos que llamarás al DAO (stubs)
    private static void listarCategorias() {
        System.out.println("CATEGORIAS: ");

        for (Categoria c : categoriaDAO.listarCategorias()) {
            System.out.println(c.getId_categoria() + " - " + c.getNombre());
        }
    }

    private static void agregarCategoria(Scanner sc) {
        sc.nextLine(); //limpiando bufer
        System.out.println("AGREGAR CATEGORIA");

        System.out.print("INGRESE NOMBRE DE CATEGORIA: ");

        Categoria c = Categoria.builder()
                .nombre(sc.nextLine())
                .build();

        categoriaDAO.agregarCategoria(c);

        System.out.println("Categoria agregada correctamente.\n");
    }

    private static void buscarCategoria(Scanner sc) {
        sc.nextLine(); //limpiando bufer
        Categoria encontrado = null;
        int id;

        do {
            System.out.print("Ingrese el id: ");
            id = sc.nextInt();

            encontrado = categoriaDAO.buscarCategoriaPorID(id);

            if (encontrado == null) {
                System.out.printf("ID incorrecto. Intente nuevamente \n");
            }

        } while (encontrado == null);

        System.out.printf("Categoria con id %d encontrada: \n", id);
        System.out.printf("ID: %d  |  Categoria: %s\n",
                encontrado.getId_categoria(),
                encontrado.getNombre());
    }

    private static void actualizarCategoria(Scanner sc) {
        Categoria encontrado;

        do {
            System.out.print("Ingresa el ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = categoriaDAO.buscarCategoriaPorID(id);

            if (encontrado == null) {
                System.out.printf("No se encontró la categoría con ID: %d... vuelve a intentarlo\n", id);

            }
        } while (encontrado == null);

        System.out.printf("Categoria encontrada:\nID: %d | Categoria: %s\n",
                encontrado.getId_categoria(), encontrado.getNombre());

        System.out.println("Ingresa nuevo nombre de categoria: ");
        String nuevaCategoria = sc.nextLine();

        categoriaDAO.actualizarCategoria(encontrado.getId_categoria(), nuevaCategoria);

        System.out.println("Categoria actualizada correctamente");
    }

    private static void eliminarCategoria(Scanner sc) {
        Categoria encontrado;

        do {
            System.out.print("Ingresa el ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = categoriaDAO.buscarCategoriaPorID(id);

            if (encontrado == null) {
                System.out.printf("No se encontró la categoría con ID: %d... vuelve a intentarlo\n", id);

            }
        } while (encontrado == null);

        System.out.printf("Categoria encontrada:\nID: %d | Categoria: %s\n",
                encontrado.getId_categoria(), encontrado.getNombre());

        String input;
        do {
            System.out.println("¿Seguro de eliminar? S/N");
            input = sc.nextLine().trim();
        } while (input.isEmpty());

        char confirmar = Character.toUpperCase(input.charAt(0));

        if (confirmar == 'S') {
            categoriaDAO.eliminarCategoriaPorId(encontrado.getId_categoria());
            System.out.println("Categoría eliminada correctamente.");
        } else {
            System.out.println("Operación cancelada. No se eliminó la categoría.");
        }
    }

    private static void listarProductos() {
        System.out.println("PRODUCTOS: ");

        for (Producto p : productoDAO.listarProducto()) {
            System.out.printf("%-5d | %-25s | %-10.2f | %-20s | %-5d\n",
                    p.getId_producto(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getCategoria().getNombre(),
                    p.getStock());
        }
    }

    private static void agregarProducto(Scanner sc) {
        List<Categoria> categorias = categoriaDAO.listarCategorias();
        System.out.println("AGREGAR PRODUCTO");

        System.out.println("CATEGORÍAS DISPONIBLES:");
        for (Categoria c : categorias) {
            System.out.println(c.getId_categoria() + " - " + c.getNombre());
        }

        System.out.print("INGRESE NOMBRE DEL PRODUCTO: ");
        String descripcion = sc.nextLine();

        System.out.print("INGRESE EL PRECIO: ");
        BigDecimal precio = sc.nextBigDecimal();

        System.out.print("INGRESA EL STOCK: ");
        int stock = sc.nextInt();

        System.out.print("INGRESA EL ID CATEGORIA");
        int idCat = sc.nextInt();

        Categoria cat = categoriaDAO.buscarCategoriaPorID(idCat);

        if (cat == null) {
            System.out.println("ID no válido. No existe la categoría.");
            return;
        }

        Producto p = Producto.builder()
                .descripcion(descripcion)
                .precio(precio)
                .stock(stock)
                .categoria(cat)
                .build();

        int nuevoId = productoDAO.agregarProducto(p);

        if (nuevoId > 0) {
            System.out.println("Producto registrado con ID: " + nuevoId);
        } else {
            System.out.println("Error al registrar producto.");
        }
    }

    private static void buscarProducto(Scanner sc) {
        Producto encontrado = null;
        int id;

        do {
            System.out.print("Ingrese el id: ");
            id = sc.nextInt();

            encontrado = productoDAO.buscarProductoPorId(id);

            if (encontrado == null) {
                System.out.printf("ID incorrecto. Intente nuevamente \n");
            }
        } while (encontrado == null);

        System.out.printf("Producto con id %d encontrado: \n", id);
        System.out.printf("%-5s %-25s %-15s %-22s\n", "ID", "PRODUCTO", "PRECIO", "CATEGORIA");
        System.out.printf("%-5s %-25s %-15s %-22s\n",
                encontrado.getId_producto(), encontrado.getDescripcion(), encontrado.getPrecio(), encontrado.getCategoria().getNombre());
    }

    private static void listarProductosPorCategoria(Scanner sc) {
        System.out.println("PRODUCTOS POR CATEGORIA: ");
        System.out.println("CATEGORIAS DISPONIBLES: ");

        List<Categoria> lista = categoriaDAO.listarCategorias();

        for (Categoria c : lista) {
            System.out.println(c.getId_categoria() + " - " + c.getNombre());
        }

        sc.nextLine();
        System.out.print("Ingresa la categoria: ");
        String cat = sc.nextLine();
        for (Producto p : productoDAO.listarProductoPorCategoria(cat)) {
            System.out.printf("%-5d | %-25s | %-10.2f | %-20s | %-5d\n",
                    p.getId_producto(),
                    p.getDescripcion(),
                    p.getPrecio(),
                    p.getCategoria().getNombre(),
                    p.getStock());
        }
    }

    private static void actualizarProducto(Scanner sc) {
        Producto encontrado;

        do {
            System.out.print("Ingresa el ID del producto: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = productoDAO.buscarProductoPorId(id);

            if (encontrado == null) {
                System.out.println("No existe un producto con ese ID. Intente nuevamente.");
            }
        } while (encontrado == null);

        System.out.println("\nProducto encontrado:");
        System.out.printf("ID: %d | Nombre: %s | Precio: %.2f | Stock: %d | Categoria: %s\n",
                encontrado.getId_producto(),
                encontrado.getDescripcion(),
                encontrado.getPrecio(),
                encontrado.getStock(),
                encontrado.getCategoria().getNombre());

        System.out.print("\nNuevo nombre: ");
        String nuevoNombre = sc.nextLine();

        System.out.print("Nuevo precio: ");
        BigDecimal nuevoPrecio = sc.nextBigDecimal();

        System.out.print("Nuevo stock: ");
        int nuevoStock = sc.nextInt();
        sc.nextLine();

        System.out.print("Nuevo ID de categoría: ");
        int nuevaCategoria = sc.nextInt();
        sc.nextLine();

        Categoria cat = categoriaDAO.buscarCategoriaPorID(nuevaCategoria);
        if (cat == null) {
            System.out.println("Categoría inválida.");
            return;
        }

        encontrado.setDescripcion(nuevoNombre);
        encontrado.setPrecio(nuevoPrecio);
        encontrado.setStock(nuevoStock);
        encontrado.setCategoria(cat);

        boolean ok = productoDAO.actualizarProducto(encontrado);
        if (ok) {
            System.out.println("Producto actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar producto.");
        }
    }

    private static void eliminarProducto(Scanner sc) {
        Producto encontrado;

        do {
            System.out.print("Ingresa el ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = productoDAO.buscarProductoPorId(id);

            if (encontrado == null) {
                System.out.printf("No se encontró el producto con ID: %d... vuelve a intentarlo\n", id);

            }
        } while (encontrado == null);

        System.out.println("\nProducto encontrado");

        System.out.printf("%-5s %-25s %-15s %-22s\n", "ID", "PRODUCTO", "PRECIO", "CATEGORIA");
        System.out.printf("%-5s %-25s %-15s %-22s\n",
                encontrado.getId_producto(), encontrado.getDescripcion(), encontrado.getPrecio(), encontrado.getCategoria().getNombre());

        String input;
        do {
            System.out.print("\nSeguro de eliminar? S/N: ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());

        char confirmar = Character.toUpperCase(input.charAt(0));

        if (confirmar == 'S') {
            productoDAO.eliminarProductoPorId(encontrado.getId_producto());
            System.out.println("Producto eliminado correctamente.");
        } else {
            System.out.println("Operacion cancelada. No se elimino el producto.");
        }
    }

    private static void listarClientes() {
        System.out.println("CLIENTES: ");

        for (Cliente c : clienteDAO.listarClientes()) {
            System.out.printf("%-5d | %-25s | %-16s | %-20s\n",
                    c.getId_cliente(),
                    c.getNombres(),
                    c.getTelefono(),
                    c.getCorreo());
        }
    }

    private static void agregarCliente(Scanner sc) {
        sc.nextLine();
        System.out.println("\nAGREGAR CLIENTE");

        System.out.print("Nombres: ");
        String nom = sc.nextLine();

        System.out.print("Telefono: ");
        String tel = sc.nextLine();

        System.out.print("Correo: ");
        String correo = sc.nextLine();

        Cliente c = Cliente.builder()
                .nombres(nom)
                .telefono(tel)
                .correo(correo)
                .build();

        int nuevoId = clienteDAO.agregarCliente(c);

        if (nuevoId > 0) {
            System.out.println("Cliente registrado con ID: " + nuevoId);
        } else {
            System.out.println("Error al registrar cliente.");
        }
    }

    private static void buscarCliente(Scanner sc) {
        Cliente encontrado = null;
        int id;

        do {
            System.out.print("Ingrese el id: ");
            id = sc.nextInt();

            encontrado = clienteDAO.buscarClientePorId(id);

            if (encontrado == null) {
                System.out.printf("ID incorrecto. Intente nuevamente \n");
            }

        } while (encontrado == null);

        System.out.printf("Cliente con id %d encontrado: \n", id);
        System.out.printf("ID: %d  |  Nombres: %s | Telefono: %s | Correo: %s\n",
                encontrado.getId_cliente(),
                encontrado.getNombres(),
                encontrado.getTelefono(),
                encontrado.getCorreo());
    }

    private static void actualizarCliente(Scanner sc) {
        Cliente encontrado;

        do {
            System.out.print("Ingresa el ID del cliente: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = clienteDAO.buscarClientePorId(id);

            if (encontrado == null) {
                System.out.println("Cliente no encontrado. Intente nuevamente.");
            }
        } while (encontrado == null);

        System.out.println("Cliente encontrado:");
        System.out.printf("ID: %d | Nombre: %s | Teléfono: %s | Correo: %s\n",
                encontrado.getId_cliente(),
                encontrado.getNombres(),
                encontrado.getTelefono(),
                encontrado.getCorreo());

        System.out.print("Nuevo nombre: ");
        String nom = sc.nextLine();

        System.out.print("Nuevo teléfono: ");
        String tel = sc.nextLine();

        System.out.print("Nuevo correo: ");
        String correo = sc.nextLine();

        encontrado.setNombres(nom);
        encontrado.setTelefono(tel);
        encontrado.setCorreo(correo);

        boolean ok = clienteDAO.actualizarCliente(encontrado);

        if (ok) {
            System.out.println("Cliente actualizado correctamente.");
        } else {
            System.out.println("Error al actualizar cliente.");
        }
    }

    private static void eliminarCliente(Scanner sc) {
        Cliente encontrado;

        do {
            System.out.print("Ingresa el ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = clienteDAO.buscarClientePorId(id);

            if (encontrado == null) {
                System.out.printf("No se encontró Cliente con ID: %d... vuelve a intentarlo\n", id);

            }
        } while (encontrado == null);

        System.out.println("\nCliente encontrado");

        System.out.printf("%-5s %-25s %-18s %-22s\n", "ID", "CLIENTE", "PRECIO", "CATEGORIA");
        System.out.printf("%-5s %-25s %-18s %-22s\n",
                encontrado.getId_cliente(), encontrado.getNombres(), encontrado.getTelefono(), encontrado.getCorreo());

        String input;
        do {
            System.out.print("\nSeguro de eliminar? S/N: ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());

        char confirmar = Character.toUpperCase(input.charAt(0));

        if (confirmar == 'S') {
            clienteDAO.eliminarClientePorId(encontrado.getId_cliente());
            System.out.println("Cliente eliminado correctamente.");
        } else {
            System.out.println("Operacion cancelada. No se elimino Cliente.");
        }
    }

    private static void registrarVenta(Scanner sc) {
        sc.nextLine();

        System.out.println("\nREGISTRAR VENTA");

        System.out.print("ID Cliente: ");
        int idCli = sc.nextInt();

        System.out.print("ID Producto: ");
        int idProd = sc.nextInt();

        System.out.print("Cantidad: ");
        int cant = sc.nextInt();
        sc.nextLine();

        Cliente cli = clienteDAO.buscarClientePorId(idCli);
        if (cli == null) {
            System.out.println("Cliente no encontrado.");
            return;
        }

        Producto prod = productoDAO.buscarProductoPorId(idProd);
        if (prod == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        Venta v = Venta.builder()
                .cliente(cli)
                .producto(prod)
                .cantidad(cant)
                .fecha(LocalDate.now())
                .build();

        int nuevoId = ventaDAO.registrarVenta(v);

        if (nuevoId > 0) {
            System.out.println("Venta registrada con ID: " + nuevoId);
        } else {
            System.out.println("Error al registrar venta.");
        }
    }

    private static void listarVentas() {
        System.out.println("\nVENTAS: ");

        List<VentaListadoDTO> lista = ventaDAO.listarVentas();

        System.out.printf("%-5s %-20s %-20s %-12s %-10s %-10s %-12s\n",
                "ID", "CLIENTE", "PRODUCTO", "PRECIO", "CANT", "TOTAL", "FECHA");

        for (VentaListadoDTO v : lista) {
            System.out.printf("%-5d %-20s %-20s %-12.2f %-10d %-10.2f %-12s\n",
                    v.getIdVenta(),
                    v.getCliente(),
                    v.getProducto(),
                    v.getPrecioUnit(),
                    v.getCantidad(),
                    v.getTotal(),
                    v.getFecha());
        }
    }

    private static void buscarVentaPorCliente(Scanner sc) {
        sc.nextLine();
        System.out.print("Ingrese nombre del cliente: ");
        String nombre = sc.nextLine();

        List<VentaListadoDTO> lista = ventaDAO.buscarVentaPorCliente(nombre);

        if (lista.isEmpty()) {
            System.out.println("No hay ventas para ese cliente.");
            return;
        }

        System.out.printf("%-5s %-20s %-20s %-12s %-10s %-10s %-12s\n",
                "ID", "CLIENTE", "PRODUCTO", "PRECIO", "CANT", "TOTAL", "FECHA");

        for (VentaListadoDTO v : lista) {
            System.out.printf("%-5d %-20s %-20s %-12.2f %-10d %-10.2f %-12s\n",
                    v.getIdVenta(),
                    v.getCliente(),
                    v.getProducto(),
                    v.getPrecioUnit(),
                    v.getCantidad(),
                    v.getTotal(),
                    v.getFecha());
        }
    }

    private static void buscarVentaPorFecha(Scanner sc) {
        sc.nextLine();
        System.out.print("Ingrese fecha (YYYY-MM-DD): ");
        String fecha = sc.nextLine();

        List<VentaListadoDTO> lista = ventaDAO.buscarVentaPorFecha(fecha);

        if (lista.isEmpty()) {
            System.out.println("No hay ventas en esa fecha.");
            return;
        }

        System.out.printf("%-5s %-20s %-20s %-12s %-10s %-10s %-12s\n",
                "ID", "CLIENTE", "PRODUCTO", "PRECIO", "CANT", "TOTAL", "FECHA");

        for (VentaListadoDTO v : lista) {
            System.out.printf("%-5d %-20s %-20s %-12.2f %-10d %-10.2f %-12s\n",
                    v.getIdVenta(),
                    v.getCliente(),
                    v.getProducto(),
                    v.getPrecioUnit(),
                    v.getCantidad(),
                    v.getTotal(),
                    v.getFecha());
        }
    }

    private static void eliminarVenta(Scanner sc) {
        VentaListadoDTO encontrado;

        do {
            System.out.print("Ingresa el ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            encontrado = ventaDAO.buscarVentaPorId(id);

            if (encontrado == null) {
                System.out.printf("No se encontró la venta con ID: %d... vuelve a intentarlo\n", id);

            }
        } while (encontrado == null);

        System.out.println("\nVenta encontrada");

        System.out.printf("%-5s %-20s %-20s %-12s %-10s %-10s\n",
                "ID", "CLIENTE", "PRODUCTO", "PRECIO", "CANT", "TOTAL");
        System.out.printf("%-5d %-20s %-20s %-12.2f %-10d %-10.2f\n",
                encontrado.getIdVenta(),
                encontrado.getCliente(),
                encontrado.getProducto(),
                encontrado.getPrecioUnit(),
                encontrado.getCantidad(),
                encontrado.getTotal());

        String input;
        do {
            System.out.print("\nSeguro de eliminar? S/N: ");
            input = sc.nextLine().trim();
        } while (input.isEmpty());

        char confirmar = Character.toUpperCase(input.charAt(0));

        if (confirmar == 'S') {
            ventaDAO.eliminarVenta(encontrado.getIdVenta());
            System.out.println("Venta eliminada correctamente.");
        } else {
            System.out.println("Operacion cancelada. No se elimino la venta.");
        }
    }
}
