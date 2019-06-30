package controladorVistas;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.usuarios.Usuario;
import java.util.LinkedList;


public class ControladorTablaComprasReservas {
	private Usuario usuario;
	public String nombreVentana(String tipoVentana) {
		
	   if(tipoVentana.equals("compras")){
            return "Compras de: ";
        }
        else{
            return "Reservas de: ";
        }
		
	}

	public String nombreUsuario() {
            return usuario.getNombre();
		
	}


	public LinkedList<Asiento> iniciarTabla(Usuario usuarioLogeado, String tipoVentana) {
        if(tipoVentana.equals("reservas")){
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
