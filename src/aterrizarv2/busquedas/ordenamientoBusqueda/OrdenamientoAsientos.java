package aterrizarv2.busquedas.ordenamientoBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.List;
import java.util.TreeMap;

public interface OrdenamientoAsientos {
    
    public void ordenar(List<AsientoVueloFullData> lista,TreeMap<String,Integer> asientosVendidosVuelo);
}
