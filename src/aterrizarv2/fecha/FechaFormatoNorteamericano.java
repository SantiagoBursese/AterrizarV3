package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;

public class FechaFormatoNorteamericano extends Fecha{
    final static String DATE_FORMAT = "MM-dd-yyyy";
    
    public FechaFormatoNorteamericano(String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException {
        super(fecha,DATE_FORMAT);
        this.dia = substraerDia(fecha);
        this.mes = substraerMes(fecha);
        this.anio = substraerAnio(fecha);
    }
    protected String substraerDia(String fecha){
        return fecha.substring(3,5);
    }
    protected String substraerMes(String fecha){
        return fecha.substring(0,2);
    }
    protected String substraerAnio(String fecha){
        return fecha.substring(6,10);
    }
}
