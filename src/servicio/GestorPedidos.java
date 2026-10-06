package servicio;

import excepciones.DatoInvalidoException;
import modelo.LineaPedido;
import modelo.Pedido;
import modelo.Producto;
import java.util.ArrayList;
import java.util.List;

public class GestorPedidos {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int siguienteId = 1;

    public Pedido nuevoPedido() {
        return new Pedido(siguienteId);
    }

    public void confirmar(Pedido pedido) throws DatoInvalidoException {
        for (LineaPedido linea : pedido.getLineas()) {
            Producto producto = linea.getProducto();
            producto.setStock(producto.getStock() - linea.getCantidad());
        }
        pedidos.add(pedido);
        siguienteId++;
    }

    public List<Pedido> listar() {
        return pedidos;
    }
}