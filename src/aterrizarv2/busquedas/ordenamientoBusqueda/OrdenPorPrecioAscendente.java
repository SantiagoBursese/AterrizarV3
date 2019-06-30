package aterrizarv2.busquedas.ordenamientoBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;

class ComparadorPorPrecioAscendente implements Comparator<AsientoVueloFullData>{

    @Override
    public int compare(AsientoVueloFullData o1, AsientoVueloFullData o2) {
        return Double.compare(o1.getAsiento().getPrecio().getPrecioAsiento(), o2.getAsiento().getPrecio().getPrecioAsiento());
    }

}

public class OrdenPorPrecioAscendente implements OrdenamientoAsientos {

    @Override
    public void ordenar(List<AsientoVueloFullData> lista,TreeMap<String,Integer> asientosVendidosVuelo) {
        Collections.sort(lista,new ComparadorPorPrecioAscendente());
    }
    
}
