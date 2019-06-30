package aterrizarv2.fecha.excepcionesFecha;


public class FormatoFechaIncorrectoException extends RuntimeException{

    public FormatoFechaIncorrectoException() {
    }

    public FormatoFechaIncorrectoException(String mensaje) {
        super(mensaje);
    }
    
}
