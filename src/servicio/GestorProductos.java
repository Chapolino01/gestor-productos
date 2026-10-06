package servicio;

import excepciones.DatoInvalidoException;
import excepciones.ProductoNoEncontradoException;
import modelo.Producto;
import modelo.ProductoPerecedero;
import java.util.ArrayList;
import java.util.List;

public class GestorProductos {
    private final List<Producto> productos = new ArrayList<>();
    private int siguienteId = 1;

    public Producto crear(String nombre, double precio, int stock) throws DatoInvalidoException {
        Producto producto = new Producto(siguienteId, nombre, precio, stock);
        productos.add(producto);
        siguienteId++;
        return producto;
    }

    public Producto crearPerecedero(String nombre, double precio, int stock, int diasParaVencer)
            throws DatoInvalidoException {
        Producto producto = new ProductoPerecedero(siguienteId, nombre, precio, stock, diasParaVencer);
        productos.add(producto);
        siguienteId++;
        return producto;
    }

    public List<Producto> listar() {
        return productos;
    }

    public Producto buscar(int id) throws ProductoNoEncontradoException {
        for (Producto producto : productos) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        throw new ProductoNoEncontradoException(id);
    }

    public void actualizar(int id, String nombre, double precio, int stock)
            throws ProductoNoEncontradoException, DatoInvalidoException {
        Producto producto = buscar(id);
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
    }

    public void eliminar(int id) throws ProductoNoEncontradoException {
        productos.remove(buscar(id));
    }
}