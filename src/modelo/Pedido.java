package modelo;

import excepciones.DatoInvalidoException;
import excepciones.StockInsuficienteException;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final int id;
    private final List<LineaPedido> lineas = new ArrayList<>();

    public Pedido(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineas() {
        return lineas;
    }

    public void agregarLinea(Producto producto, int cantidad)
            throws DatoInvalidoException, StockInsuficienteException {
        if (cantidad <= 0) {
            throw new DatoInvalidoException("La cantidad debe ser mayor a cero.");
        }
        if (cantidadEnPedido(producto) + cantidad > producto.getStock()) {
            throw new StockInsuficienteException(producto.getNombre(), producto.getStock());
        }
        lineas.add(new LineaPedido(producto, cantidad));
    }

    private int cantidadEnPedido(Producto producto) {
        int total = 0;
        for (LineaPedido linea : lineas) {
            if (linea.getProducto().getId() == producto.getId()) {
                total += linea.getCantidad();
            }
        }
        return total;
    }

    public double getTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) {
            total += linea.getSubtotal();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Pedido #" + id + "\n");
        for (LineaPedido linea : lineas) {
            sb.append(linea).append("\n");
        }
        sb.append(String.format("  TOTAL: $%.2f", getTotal()));
        return sb.toString();
    }
}