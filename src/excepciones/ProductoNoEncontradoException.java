package excepciones;

public class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(int id) {
        super("No existe un producto con ID " + id + ".");
    }
}