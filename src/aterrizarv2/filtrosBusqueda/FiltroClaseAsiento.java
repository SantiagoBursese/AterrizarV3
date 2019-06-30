package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;

public class FiltroClaseAsiento implements FiltroBusqueda{
    private ClaseAsiento clase;

    public FiltroClaseAsiento(ClaseAsiento clase) {
        this.clase = clase;
    }

    public ClaseAsiento getClase() {
        return clase;
    }
    
    
    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getClase().getClaseAsiento() == clase.getClaseAsiento();
    }
    
}
