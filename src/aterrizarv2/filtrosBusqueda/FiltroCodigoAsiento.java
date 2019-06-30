package aterrizarv2.filtrosBusqueda;

import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroCodigoAsiento implements FiltroBusqueda {
    private CodigoAsiento codigo;

    public FiltroCodigoAsiento(CodigoAsiento codigo) {
        this.codigo = codigo;
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getAsiento().getCodigo() == codigo;
    }
    
}
