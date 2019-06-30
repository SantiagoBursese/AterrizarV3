package aterrizarv2.vuelos;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.RequisitoCargaAsientos;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import java.util.LinkedList;


public class Vuelo {
    private LinkedList<Asiento> asientos;
    private String origen;
    private String destino;
    private FechaFlexible fechaSalida;
    private FechaFlexible fechaLlegada;
    private Hora horaSalida;
    private Hora horaLlegada;

    public Vuelo(String origen, String destino, FechaFlexible fechaSalida, FechaFlexible fechaLlegada, Hora horaSalida, Hora horaLlegada) {
        this.asientos = new LinkedList<>();
        this.origen = origen;
        this.destino = destino;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.horaSalida = horaSalida;
        this.horaLlegada = horaLlegada;
    }
    
    public String obtenerSalidaLatinoamericano(){
        return fechaSalida.representacionEnLatinoamericano();
    }
    
    public LinkedList<AsientoVueloFullData> getDatosAsientoVuelo(){
        LinkedList<AsientoVueloFullData> datoAsientoVuelo = new LinkedList<>();
        asientos.forEach(asiento ->{
            datoAsientoVuelo.add(new AsientoVueloFullData(asiento, origen, destino, fechaSalida, horaSalida));
        });
        return datoAsientoVuelo;
    }
   
    
    public void cargarAsientos(Aerolinea aerolinea,RequisitoCargaAsientos tipoCarga) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, TipoPedidoInvalidaException, FechaNoValidaException, FormatoFechaIncorrectoException, FormatoHoraIncorrectoException, HoraInvalidaException{
        
        String[][] asientosVuelo = aerolinea.asientosDisponibles(this, tipoCarga);
        
        //Cada aerolinea con el resultado dado me tiene que devolver una lista de asientos
        asientos.addAll(aerolinea.devolverAsiento(asientosVuelo));
    }
    
    
    public void agregarAsiento(Asiento asientoVuelo){
        asientos.add(asientoVuelo);
    }

    public LinkedList<Asiento> getAsientosVuelo() {
        return asientos;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getFechaSalida() {
        return fechaSalida.representacionEnIso();
    }
    
    public String getFechaLlegada() {
        return fechaLlegada.representacionEnIso();
    }
    
    public String getHoraSalida() {
        return horaSalida.getHoraFormatoString();
    }
    
    public String getHoraLLegada() {
        return horaLlegada.getHoraFormatoString();
    }
    
}
