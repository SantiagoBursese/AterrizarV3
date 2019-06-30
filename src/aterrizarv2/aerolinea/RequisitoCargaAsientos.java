package aterrizarv2.aerolinea;

import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;


public class RequisitoCargaAsientos {
    private String tipoCarga;
    private final String[] cargasAceptables = {"NULL","OrigenYDestino","Origen" };

    public RequisitoCargaAsientos(String tipoCarga) throws TipoPedidoInvalidaException {
        if(!esCargaAceptable(tipoCarga)){
            throw new TipoPedidoInvalidaException("El tipo de pedido " + tipoCarga + " no se reconoce en el sistema");
        }
        this.tipoCarga = tipoCarga;
    }
    
    private boolean esCargaAceptable(String tipoCarga){
        boolean cumpleRequisitos = false;
        for(int i= 0 ; i < cargasAceptables.length && !cumpleRequisitos; i++){
            cumpleRequisitos = cargasAceptables[i].equals(tipoCarga);
        }
        return cumpleRequisitos;
    }
    public String getTipoCarga() {
        return tipoCarga;
    }
    
    
}
