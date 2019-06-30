package aterrizarv2.asientos;

import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;


public class PrecioAsiento {
    protected double precioAsiento;

    public PrecioAsiento(double precioAsiento) throws PrecioNegativoException {
        if(precioAsiento < 0){
            throw new PrecioNegativoException("El precio del asiento no puede ser negativo");
        }
        this.precioAsiento = precioAsiento;
    }

    public double getPrecioAsiento() {
        return precioAsiento;
    }
    
    /*
    @Override
    public boolean asientoVueloCumpleParametro(AsientoGeneralVuelo asiento) {
        return asiento.getDatosAsiento().getPrecio().getPrecioAsiento() == precioAsiento;
    }*/
    
}
