package controladorVistas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;


public class ControladorTablaComprasReservas {
    private Usuario usuario;

    public ControladorTablaComprasReservas(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public String nombreUsuario() {
        return usuario.getNombre();		
    }

    public LinkedList<Asiento> iniciarTabla(Usuario usuarioLogeado, String tipoVentana) {
        if(tipoVentana.equals("Reservas")){
            return usuarioLogeado.getAsientosReservados();
        }
        else{
             return usuarioLogeado.getAsientosComprados();
        }
    }
        
    public LinkedList<Asiento> actualizarTabla(String tipoVentana){
        if(tipoVentana.equals("reservas")){
            return usuario.getAsientosReservados();
        }
        else{
             return usuario.getAsientosComprados();
        }
    }	
}
