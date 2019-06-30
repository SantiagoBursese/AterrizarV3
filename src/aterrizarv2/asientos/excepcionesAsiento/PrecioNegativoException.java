package aterrizarv2.asientos.excepcionesAsiento;


public class PrecioNegativoException extends RuntimeException{

    public PrecioNegativoException(String mensaje) {
        super(mensaje);
    }
    
}
