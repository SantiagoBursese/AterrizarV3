package aterrizarv2.vuelos;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.hora.Hora;

public class AsientoVueloFullData {
    private Asiento asiento;
    private String origen;
    private String destino;
    private FechaFlexible fechaSalida;
    private Hora horaSalida;

    public AsientoVueloFullData(Asiento asiento, String origen, String destino, FechaFlexible fechaSalida, Hora horaSalida) {
        this.asiento = asiento;
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.horaSalida = horaSalida;
    }

    public Asiento getAsiento() {
        return asiento;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public FechaFlexible getFechaSalida() {
        return fechaSalida;
    }

    public Hora getHoraSalida() {
        return horaSalida;
    }
    
    
}
