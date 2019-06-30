package aterrizarv2.fecha.excepcionesFecha;

public class FechaNoValidaException extends RuntimeException{

    public FechaNoValidaException() {
    }

    public FechaNoValidaException(String mensaje) {
        super(mensaje);
    }   
}
