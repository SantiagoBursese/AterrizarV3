package aterrizarv2.aerolinea.aerolineaLanchita;

import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.RequisitoCargaAsientos;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.usuarios.Usuario;
import aterrizarv2.vuelos.Vuelo;

public class AerolineaLanchita extends Aerolinea{
    private AerolineaLanchitaI comunicacionLanchita;
    
    public AerolineaLanchita(AerolineaLanchitaI comunicacionLanchita){
        //0,15 es el recargo de lanchita
        super(0.15,10);
        this.comunicacionLanchita = comunicacionLanchita;
    }

    @Override
    public void comprarAsiento(String codigoAsiento, Usuario usuarioAComprar) throws CodigoAsientoException, AsientoReservadoException{
        super.comprarAsiento(codigoAsiento, usuarioAComprar);
        comunicacionLanchita.comprar(codigoAsiento);
        
    }
    
    @Override
    public String[][] asientosDisponibles(Vuelo vuelo, RequisitoCargaAsientos noInteresa) {
        String origen = vuelo.getOrigen();
        String destino = vuelo.getDestino();
        String fechaSalida = vuelo.getFechaSalida();
        String fechaLlegada = vuelo.getFechaLlegada();
        String horaSalida = vuelo.getHoraSalida();
        String horaLlegada = vuelo.getHoraLLegada();
        return comunicacionLanchita.asientosDisponibles(origen,destino,fechaSalida,fechaLlegada,horaSalida,horaLlegada);
    }
    
    @Override
    public Asiento setearAsiento(String[][] asientosVuelo, int posicion) throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException{
        CodigoAsiento codigo = new CodigoAsiento(asientosVuelo[posicion][0]);
        PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosVuelo[posicion][1]));
        ClaseAsiento clase = new ClaseAsiento(asientosVuelo[posicion][2]);
        UbicacionAsiento ubicacion = new UbicacionAsiento(asientosVuelo[posicion][3]);
        EstadoAsiento estado = new EstadoAsiento(asientosVuelo[posicion][4]);
        Asiento asientoSeteado = new Asiento(clase, codigo, estado, precio, ubicacion);
        return asientoSeteado;
    }

    @Override
    public void reservarAsiento(String codigoAsiento, Usuario usuarioReserva) throws CodigoAsientoException {
        super.reservarAsiento(codigoAsiento, usuarioReserva);
        comunicacionLanchita.reservar(codigoAsiento.split("-")[0], Integer.toString(usuarioReserva.getDni()));
    }
    
    @Override
    public String getNombre(){
        return "Lanchita";
    }
    
}
