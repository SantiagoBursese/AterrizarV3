package aterrizarv2.fecha;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface DatosFecha {
    
    public String getDia();
    
    public String getMes();
    
    public String getAnio();
    
    public String representacionEnIso();
    
    public String representacionEnNorteamericano();
    
    public String representacionEnLatinoamericano();
    
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
    };
}
