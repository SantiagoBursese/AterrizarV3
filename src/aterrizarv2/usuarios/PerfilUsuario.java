package aterrizarv2.usuarios;

import aterrizarv2.busquedas.Busqueda;
import java.util.LinkedList;

public class PerfilUsuario {
    private double dineroGastado;
    private LinkedList<Busqueda> busquedas;//Guardo una lista de las busquedas que realizo x temas estadisticos

    public PerfilUsuario() {
        //La idea es que no arranque siendo vip y cuando supere la cantidad prevista
        //lo ascendemos a vip, pero inicia siendo un usuario estandar
        dineroGastado = 0;
        busquedas = new LinkedList<>();
    }
    
    public double dineroGastado(){
        return dineroGastado;
    }
    
    public void incrementarDineroGastado(double cantidad){
        dineroGastado += cantidad;
    }
    
    public LinkedList<Busqueda> busquedasRealizadas(){
        return busquedas;
    }
    
    public void agregarBusqueda(Busqueda busqueda){
        busquedas.add(busqueda);
    }

    public double getDineroGastado() {
        return dineroGastado;
    }
    
    
}
