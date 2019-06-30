package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;

public class FechaFlexible extends Fecha {
    private String DATE_FORMAT;
    public FechaFlexible(String fecha) throws FormatoFechaIncorrectoException, FechaNoValidaException {
        super();
        if(cumpleFormato(fecha, "dd/MM/yyyy") && fechaValida(fecha, "dd/MM/yyyy") ){
            FechaFormatoLatinoamericano fechaFormatoLatinoamericano = new FechaFormatoLatinoamericano(fecha);
            dia = fechaFormatoLatinoamericano.substraerDia(fecha);
            mes = fechaFormatoLatinoamericano.substraerMes(fecha);
            anio = fechaFormatoLatinoamericano.substraerAnio(fecha);
            DATE_FORMAT = "dd/MM/yyyy";
        }
        else if(cumpleFormato(fecha, "MM-dd-yyyy") && fechaValida(fecha, "MM-dd-yyyy") ){
            FechaFormatoNorteamericano fechaFormatoNorteamericano = new FechaFormatoNorteamericano(fecha);
            dia = fechaFormatoNorteamericano.substraerDia(fecha);
            mes = fechaFormatoNorteamericano.substraerMes(fecha);
            anio = fechaFormatoNorteamericano.substraerAnio(fecha);
            DATE_FORMAT = "MM-dd-yyyy";
        }
        else{
            FechaFormatoIso fechaFormatoIso= new FechaFormatoIso(fecha);
            dia = fechaFormatoIso.substraerDia(fecha);
            mes = fechaFormatoIso.substraerMes(fecha);
            anio = fechaFormatoIso.substraerAnio(fecha);
            DATE_FORMAT = "yyyy-MM-dd";
        }
    }
    
}
