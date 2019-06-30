package aterrizarv2.busquedas.ordenamientoBusqueda;

import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;


class ComparadorPorPopularidad implements Comparator<AsientoVueloFullData>{
    private final TreeMap<String,Integer> asientosVendidosVuelo;

    public ComparadorPorPopularidad(TreeMap<String, Integer> asientosVendidosVuelo) {
        this.asientosVendidosVuelo = asientosVendidosVuelo;
    }
    
    
    @Override
    public int compare(AsientoVueloFullData asientoUno, AsientoVueloFullData asientoDos) {
        String numeroVueloUno = asientoUno.getAsiento().getCodigo().getNumeroVuelo();
        String numeroVueloDos = asientoDos.getAsiento().getCodigo().getNumeroVuelo();
        Integer cantidadVendidosVueloUno = asientosVendidosVuelo.get(numeroVueloUno);
        Integer cantidadVendidosVueloDos = asientosVendidosVuelo.get(numeroVueloDos);
        return Integer.compare(cantidadVendidosVueloDos, cantidadVendidosVueloUno);
    }
    
}


public class OrdenPorPopularidad implements OrdenamientoAsientos {

    @Override
    public void ordenar(List<AsientoVueloFullData> lista,TreeMap<String,Integer> asientosVendidosVuelo) {
        Collections.sort(lista,new ComparadorPorPopularidad(asientosVendidosVuelo));
    }
    
}
