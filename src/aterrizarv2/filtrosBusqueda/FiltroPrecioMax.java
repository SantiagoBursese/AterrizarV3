package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroPrecioMax implements FiltroBusqueda{
    private PrecioAsiento precioMax;

    public FiltroPrecioMax(PrecioAsiento precioMax) {
        this.precioMax = precioMax;
    }
    
    
    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getPrecio().getPrecioAsiento() <= precioMax.getPrecioAsiento();
    }
    
}
