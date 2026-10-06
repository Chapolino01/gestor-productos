package modelo;

import excepciones.DatoInvalidoException;

public class ProductoPerecedero extends Producto {
    private static final int DIAS_PARA_DESCUENTO = 3;
    private static final double DESCUENTO = 0.20;

    private int diasParaVencer;

    public ProductoPerecedero(int id, String nombre, double precio, int stock, int diasParaVencer)
            throws DatoInvalidoException {
        super(id, nombre, precio, stock);
        setDiasParaVencer(diasParaVencer);
    }

    public int getDiasParaVencer() {
        return diasParaVencer;
    }

    public void setDiasParaVencer(int diasParaVencer) throws DatoInvalidoException {
        if (diasParaVencer < 0) {
            throw new DatoInvalidoException("Los dias para vencer no pueden ser negativos.");
        }
        this.diasParaVencer = diasParaVencer;
    }

    @Override
    public double getPrecioFinal() {
        if (diasParaVencer <= DIAS_PARA_DESCUENTO) {
            return getPrecio() * (1 - DESCUENTO);
        }
        return getPrecio();
    }

    @Override
    public String getTipo() {
        return "Perecedero (vence en " + diasParaVencer + " dias)";
    }
}