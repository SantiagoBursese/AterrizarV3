package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroUbicacionAsiento implements FiltroBusqueda {
    private UbicacionAsiento ubicacion;

    public FiltroUbicacionAsiento(UbicacionAsiento ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    
    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getUbicacion().getUbicacionAsiento() == ubicacion.getUbicacionAsiento();
    }
    
}
