package aterrizarv2.asientos;

import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;

public class EstadoAsiento {
    private EnumEstadoAsiento estadoAsiento;

    public EstadoAsiento() {
        this.estadoAsiento = EnumEstadoAsiento.DISPONIBLE;
    }
    
    public EstadoAsiento(String estado) throws EstadoAsientoInvalidaException{
        if("D".equals(estado)){
            estadoAsiento = EnumEstadoAsiento.DISPONIBLE;
        }
        else if("R".equals(estado)){
            estadoAsiento = EnumEstadoAsiento.RESERVADO;
        }
        else{
            throw new EstadoAsientoInvalidaException("El acronimo especificado no hace referencia a ninguna ubicacion de asiento valida");
        }
    }
    
    public void sobrereservarAsiento(){
        if(!(estadoAsiento == EnumEstadoAsiento.RESERVADO)){
            throw new AsientoNoReservadoException("No se puede sobrereservar un asiento no reservado");
        }
        estadoAsiento = EnumEstadoAsiento.SOBRERESERVADO;
    }
    
    
    public void reservarAsiento(){
        if(!(estadoAsiento == EnumEstadoAsiento.DISPONIBLE)){
            throw new AsientoNoDisponibleException("El asiento no se encuentra disponible para reservar");
        }
        if(estadoAsiento == EnumEstadoAsiento.SOBRERESERVADO){
            throw new AsientoNoDisponibleException ("El asiento esta sobrereservado");
        }
        estadoAsiento = EnumEstadoAsiento.RESERVADO;
    }
    
    public boolean estaReservado(){
        return estadoAsiento == EnumEstadoAsiento.RESERVADO;
    }
    
    public boolean estaDisponible() {
        return estadoAsiento == EnumEstadoAsiento.DISPONIBLE;
    }
    
    public boolean estaSobrereservado(){
        return estadoAsiento == EnumEstadoAsiento.SOBRERESERVADO;
    }
    
    public void venderAsiento(){
        estadoAsiento = EnumEstadoAsiento.VENDIDO;
    }
    
    public void asientoDisponible(){
        estadoAsiento = EnumEstadoAsiento.DISPONIBLE;
    }
    
    public boolean asientoVendido(){
        return estadoAsiento == EnumEstadoAsiento.VENDIDO;
    }
    
    public EnumEstadoAsiento getEstadoAsiento() {
        return estadoAsiento;
    }
    
    /*
    @Override
    public boolean asientoVueloCumpleParametro(AsientoGeneralVuelo asiento) {
        //Es decir, si solo busca disponibles devolvemos true si el estado de nuestro asiento es disponible
        //Si buscamos incluir los reservados tambien devolvemos true ya que queremos mostrar todos
        EstadoAsientoVuelo estadoPermite = asiento.getDatosAsiento().getEstado().getEstadoAsiento();
        if(estadoPermite == EstadoAsientoVuelo.VENDIDO)return false;//Estos no se muestran en busquedas
        return (estadoPermite == EstadoAsientoVuelo.DISPONIBLE) ? estadoPermite == estadoAsiento : true  ;
    }*/

}
