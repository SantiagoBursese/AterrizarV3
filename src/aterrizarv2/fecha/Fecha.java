package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Fecha implements DatosFecha{
    protected String dia;
    protected String mes;
    protected String anio;

    public Fecha() {}
    
    public Fecha(String fecha, String dateFormat) throws FormatoFechaIncorrectoException, FechaNoValidaException {
        if(!cumpleFormato(fecha,dateFormat)){
            throw new FormatoFechaIncorrectoException("La fecha propiciada no cumple con "
                    + "el formato " + dateFormat);
        }
        if(!fechaValida(fecha,dateFormat)){
            throw new FechaNoValidaException("La fecha propiciada no es valida");
        }
       
    }
    
    protected boolean cumpleFormato(String fecha,String dateFormat){
        //De los numeros se encarga fecha valida, el formato nada mas mira
        //que sea cooerente en relacion al formato dado
        boolean cumpleFormato = true;
        char separador = getSeparadorFormato(dateFormat);
        if(fecha.length() != dateFormat.length())cumpleFormato = false;
        for(int i = 0 ; i < dateFormat.length() && cumpleFormato; i++){
            if(dateFormat.charAt(i) == separador ){
                cumpleFormato = (fecha.charAt(i) == separador);
            }
            else{
                cumpleFormato = Character.isDigit(fecha.charAt(i));
            }
        }
        return cumpleFormato;
    }
    
    private char getSeparadorFormato(String formato){
        for(int i = 0 ; i < formato.length() ; i++){
            if(formato.charAt(i) == '/' || formato.charAt(i) == '-' ) return formato.charAt(i);
        }
        //Como los formatos son constantes y tienen separadaroes no va a llegar aca, es para
        //no crear una variable y retornar eso, directamente retornamos en el for
        return ' ';
    }
    
    protected boolean fechaValida(String fecha, String dateFormat){
        try {
            DateFormat df = new SimpleDateFormat(dateFormat);
            df.setLenient(false);
            df.parse(fecha);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    @Override
    public String getDia() {
        return dia;
    }

    @Override
    public String getMes() {
        return mes;
    }

    @Override
    public String getAnio() {
        return anio;
    }
    
    @Override
    public String representacionEnIso(){
        return anio + "-" + mes + "-" + dia;
    }
    
    @Override
    public String representacionEnNorteamericano(){
        return mes + "-" + dia + "-" + anio;
    }
    
    @Override
    public String representacionEnLatinoamericano(){
        return dia + "/" + mes + "/" + anio;
    }
        public static int diasPasaron(DatosFecha a, DatosFecha b){
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            Date fechaInicial=dateFormat.parse(a.representacionEnIso());
            Date fechaFinal=dateFormat.parse(b.representacionEnIso());
            
            return Math.abs((int) ((fechaFinal.getTime()-fechaInicial.getTime())/86400000));
        } catch (ParseException ex) {
            Logger.getLogger(Fecha.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    };
    
    public static boolean hayDiferenciaAnios(DatosFecha a, DatosFecha b){
        return a.getAnio() != b.getAnio();
    }
    
    public static boolean hayDiferenciaMeses(DatosFecha a, DatosFecha b){
        return a.getMes() != b.getMes();
    }
    
    public static DatosFecha fechaAnterior(DatosFecha a, DatosFecha b){
        if(hayDiferenciaAnios(a,b)){
            return (Integer.parseInt(a.getAnio()) < Integer.parseInt(b.getAnio())) ? a : b;
        }
        else if(hayDiferenciaMeses(a,b)){
            return (Integer.parseInt(a.getMes()) < Integer.parseInt(b.getMes())) ? a : b;
        }
        else{
            return (Integer.parseInt(a.getDia())) <= Integer.parseInt(b.getDia()) ? a : b;//Si es la misma fecha retorna a
        }
    }
}
