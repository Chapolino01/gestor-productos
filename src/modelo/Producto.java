package modelo;

import excepciones.DatoInvalidoException;

public class Producto {
    private int id;
    private String nombre;
    private double precio;
    private int stock;

    public Producto(int id, String nombre, double precio, int stock) throws DatoInvalidoException {
        this.id = id;
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws DatoInvalidoException {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new DatoInvalidoException("El nombre no puede estar vacio.");
        }
        this.nombre = nombre.trim();
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) throws DatoInvalidoException {
        if (precio < 0) {
            throw new DatoInvalidoException("El precio no puede ser negativo.");
        }
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) throws DatoInvalidoException {
        if (stock < 0) {
            throw new DatoInvalidoException("El stock no puede ser negativo.");
        }
        this.stock = stock;
    }

    public double getPrecioFinal() {
        return precio;
    }

    public String getTipo() {
        return "General";
    }

    @Override
    public String toString() {
        return String.format("ID: %d | %s | Tipo: %s | Precio: $%.2f | Stock: %d",
                id, nombre, getTipo(), getPrecioFinal(), stock);
    }
}