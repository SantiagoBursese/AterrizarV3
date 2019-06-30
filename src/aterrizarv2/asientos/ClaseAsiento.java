package aterrizarv2.asientos;

import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;


public class ClaseAsiento {
    private EnumClaseAsiento claseAsiento;
    
    public ClaseAsiento(EnumClaseAsiento claseAsiento) {
        this.claseAsiento = claseAsiento;
    }
    
    public ClaseAsiento(String clase) throws ClaseAsientoInvalidaException{
        if(clase == "P"){
            claseAsiento = EnumClaseAsiento.PRIMERA_CLASE;
        }
        else if(clase == "T"){
            claseAsiento = EnumClaseAsiento.TURISTA;
        }
        else if(clase == "E"){
            claseAsiento = EnumClaseAsiento.EJECUTIVO;
        }
        else{
            throw new ClaseAsientoInvalidaException("El acronimo especificado no hace referencia a ninguna clase de asiento valida");
        }
    }
    
    public String claseFormatoString(){
        if(claseAsiento == EnumClaseAsiento.EJECUTIVO){
            return "E";
        }
        else if(claseAsiento == EnumClaseAsiento.TURISTA){
            return "T";
        }
        return "P";
       
    }
    
    public EnumClaseAsiento getClaseAsiento() {
        return claseAsiento;
    }
    
}
