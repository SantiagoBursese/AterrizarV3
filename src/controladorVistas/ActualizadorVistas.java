package controladorVistas;

import Vistas.ReservasCompras;

public class ActualizadorVistas {
    private ControladorTablaComprasReservas ventanaCompras;
    private ControladorTablaComprasReservas ventanaReservas;
    

    public ActualizadorVistas(ControladorTablaComprasReservas ventanaReservas, ControladorTablaComprasReservas ventanaCompras) {
        this.ventanaCompras = ventanaCompras;
        this.ventanaReservas = ventanaReservas;
    }
     
	public void actualizarVistas(){
        ventanaCompras.actualizarTabla("compras");
        ventanaReservas.actualizarTabla("reservas");
    } 
}
