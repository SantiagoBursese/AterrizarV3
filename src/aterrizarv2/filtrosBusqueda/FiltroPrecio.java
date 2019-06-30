package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroPrecio implements FiltroBusqueda {
    private PrecioAsiento precio;

    public FiltroPrecio(PrecioAsiento precio) {
        this.precio = precio;
    }
    
    
    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getPrecio() == precio;
    }
    
}
