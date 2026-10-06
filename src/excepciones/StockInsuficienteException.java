package excepciones;

public class StockInsuficienteException extends Exception {
    public StockInsuficienteException(String nombre, int disponible) {
        super("Stock insuficiente de '" + nombre + "'. Disponible: " + disponible + ".");
    }
}