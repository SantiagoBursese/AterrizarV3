package aterrizarv2.filtrosBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroDestino implements FiltroBusqueda{
    private String destino;
    
    public FiltroDestino(String destino){
        this.destino = destino;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getDestino().equals(destino);
    }
    
}
