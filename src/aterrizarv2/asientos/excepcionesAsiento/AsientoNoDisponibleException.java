package aterrizarv2.asientos.excepcionesAsiento;

public class AsientoNoDisponibleException extends RuntimeException {
    public AsientoNoDisponibleException(String message){
        super(message);
    }
}
