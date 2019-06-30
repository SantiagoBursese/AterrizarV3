package aterrizarv2.filtrosBusqueda;

import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.vuelos.AsientoVueloFullData;

public class FiltroFecha implements FiltroBusqueda{
    private FechaFlexible fechaSalida;

    public FiltroFecha(FechaFlexible fechaSalida) {
        this.fechaSalida = fechaSalida;
    }
    
    public String fechaIso(){
        return fechaSalida.representacionEnIso();
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        String fechaSalidaIso = fechaSalida.representacionEnIso();
        String asientoFechaIso = asiento.getFechaSalida().representacionEnIso();
        return asientoFechaIso.equals(fechaSalidaIso);
    }
}
