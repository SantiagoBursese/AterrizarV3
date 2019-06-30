package aterrizarv2.filtrosBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroOrigen implements FiltroBusqueda {
    private String origen;
    
    public FiltroOrigen(String origen){
        this.origen = origen;
    }

    public String getDestino() {
        return origen;
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getOrigen().equals(origen);
    }
}
