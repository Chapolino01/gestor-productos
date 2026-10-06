package ui;

import excepciones.DatoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import excepciones.StockInsuficienteException;
import modelo.Pedido;
import modelo.Producto;
import servicio.GestorPedidos;
import servicio.GestorProductos;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private final Scanner scanner = new Scanner(System.in);
    private final GestorProductos gestorProductos = new GestorProductos();
    private final GestorPedidos gestorPedidos = new GestorPedidos();

    public void iniciar() {
        int opcion;
        do {
            mostrarOpciones();
            opcion = leerEntero("Elegi una opcion: ");
            ejecutar(opcion);
        } while (opcion != 0);
        System.out.println("Hasta luego.");
    }

    private void mostrarOpciones() {
        System.out.println("\n===== GESTOR DE PRODUCTOS =====");
        System.out.println("1. Crear producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Buscar producto por ID");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Crear pedido");
        System.out.println("7. Listar pedidos");
        System.out.println("0. Salir");
    }

    private void ejecutar(int opcion) {
        try {
            switch (opcion) {
                case 1:
                    crearProducto();
                    break;
                case 2:
                    listarProductos();
                    break;
                case 3:
                    buscarProducto();
                    break;
                case 4:
                    actualizarProducto();
                    break;
                case 5:
                    eliminarProducto();
                    break;
                case 6:
                    crearPedido();
                    break;
                case 7:
                    listarPedidos();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opcion invalida.");
            }
        } catch (DatoInvalidoException | ProductoNoEncontradoException | StockInsuficienteException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void crearProducto() throws DatoInvalidoException {
        String nombre = leerTexto("Nombre: ");
        double precio = leerDecimal("Precio: ");
        int stock = leerEntero("Stock: ");
        String perecedero = leerTexto("Es perecedero? (s/n): ");
        Producto producto;
        if (perecedero.equalsIgnoreCase("s")) {
            int dias = leerEntero("Dias para vencer: ");
            producto = gestorProductos.crearPerecedero(nombre, precio, stock, dias);
        } else {
            producto = gestorProductos.crear(nombre, precio, stock);
        }
        System.out.println("Producto creado: " + producto);
    }

    private void listarProductos() {
        List<Producto> productos = gestorProductos.listar();
        if (productos.isEmpty()) {
            System.out.println("No hay productos cargados.");
            return;
        }
        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    private void buscarProducto() throws ProductoNoEncontradoException {
        int id = leerEntero("ID a buscar: ");
        System.out.println(gestorProductos.buscar(id));
    }

    private void actualizarProducto() throws ProductoNoEncontradoException, DatoInvalidoException {
        int id = leerEntero("ID a actualizar: ");
        gestorProductos.buscar(id);
        String nombre = leerTexto("Nuevo nombre: ");
        double precio = leerDecimal("Nuevo precio: ");
        int stock = leerEntero("Nuevo stock: ");
        gestorProductos.actualizar(id, nombre, precio, stock);
        System.out.println("Producto actualizado.");
    }

    private void eliminarProducto() throws ProductoNoEncontradoException {
        int id = leerEntero("ID a eliminar: ");
        gestorProductos.eliminar(id);
        System.out.println("Producto eliminado.");
    }

    private void crearPedido() throws ProductoNoEncontradoException, DatoInvalidoException,
            StockInsuficienteException {
        Pedido pedido = gestorPedidos.nuevoPedido();
        String seguir;
        do {
            int id = leerEntero("ID del producto: ");
            Producto producto = gestorProductos.buscar(id);
            int cantidad = leerEntero("Cantidad: ");
            pedido.agregarLinea(producto, cantidad);
            seguir = leerTexto("Agregar otro producto? (s/n): ");
        } while (seguir.equalsIgnoreCase("s"));

        gestorPedidos.confirmar(pedido);
        System.out.println(pedido);
    }

    private void listarPedidos() {
        List<Pedido> pedidos = gestorPedidos.listar();
        if (pedidos.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            return;
        }
        for (Pedido pedido : pedidos) {
            System.out.println(pedido);
        }
    }

    private String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private int leerEntero(String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(leerTexto(mensaje).trim());
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero entero valido.");
            }
        }
    }

    private double leerDecimal(String mensaje) {
        while (true) {
            try {
                return Double.parseDouble(leerTexto(mensaje).trim().replace(',', '.'));
            } catch (NumberFormatException e) {
                System.out.println("Ingresa un numero valido.");
            }
        }
    }
}