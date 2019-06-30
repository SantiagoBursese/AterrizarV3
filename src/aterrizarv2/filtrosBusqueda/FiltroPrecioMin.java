package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroPrecioMin implements FiltroBusqueda {
    private PrecioAsiento precioMin;

    public FiltroPrecioMin(PrecioAsiento precioMin) {
        this.precioMin = precioMin;
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getPrecio().getPrecioAsiento() >= precioMin.getPrecioAsiento();
    }
    
}
