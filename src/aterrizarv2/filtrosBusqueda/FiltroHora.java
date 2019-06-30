package aterrizarv2.filtrosBusqueda;

import aterrizarv2.hora.Hora;
import aterrizarv2.vuelos.AsientoVueloFullData;


public class FiltroHora implements FiltroBusqueda {
    private Hora horaSalida;

    public FiltroHora(Hora horaSalida) {
        this.horaSalida = horaSalida;
    }
    
    public String horaDeLLegada(){
        return horaSalida.getHoraFormatoString();
    }

    @Override
    public boolean asientoVueloCumpleParametro(AsientoVueloFullData asiento) {
        return asiento.getHoraSalida().getHoraFormatoString().equals(horaSalida.getHoraFormatoString());
    }
}
