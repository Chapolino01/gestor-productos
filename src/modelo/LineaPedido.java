package modelo;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;

    public LineaPedido(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getSubtotal() {
        return producto.getPrecioFinal() * cantidad;
    }

    @Override
    public String toString() {
        return String.format("  %s x%d = $%.2f", producto.getNombre(), cantidad, getSubtotal());
    }
}