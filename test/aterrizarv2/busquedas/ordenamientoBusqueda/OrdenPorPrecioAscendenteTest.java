package aterrizarv2.busquedas.ordenamientoBusqueda;

import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.vuelos.AsientoVueloFullData;
import java.util.Arrays;
import java.util.List;
import org.hamcrest.MatcherAssert;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.BeforeClass;
import org.mockito.Mockito;

public class OrdenPorPrecioAscendenteTest {
    
    public OrdenPorPrecioAscendenteTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        
    }

    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void ordenaOrdenAscendente(){
        AsientoVueloFullData as1 = Mockito.mock(AsientoVueloFullData.class);
        AsientoVueloFullData as2 = Mockito.mock(AsientoVueloFullData.class);
        AsientoVueloFullData as3 = Mockito.mock(AsientoVueloFullData.class);
        Asiento datos1 = Mockito.mock(Asiento.class);
        Asiento datos2 = Mockito.mock(Asiento.class);
        Asiento datos3 = Mockito.mock(Asiento.class);
        PrecioAsiento precio1 = Mockito.mock(PrecioAsiento.class);
        PrecioAsiento precio2 = Mockito.mock(PrecioAsiento.class);
        PrecioAsiento precio3 = Mockito.mock(PrecioAsiento.class);
        
        
        Mockito.when(datos1.getPrecio()).thenReturn(precio1);
        Mockito.when(datos2.getPrecio()).thenReturn(precio2);
        Mockito.when(datos3.getPrecio()).thenReturn(precio3);
        
        Mockito.when(precio1.getPrecioAsiento()).thenReturn(125.12);
        Mockito.when(precio2.getPrecioAsiento()).thenReturn(145.12);
        Mockito.when(precio3.getPrecioAsiento()).thenReturn(135.12);
        
        Mockito.when(as1.getAsiento()).thenReturn(datos1);
        Mockito.when(as2.getAsiento()).thenReturn(datos2);
        Mockito.when(as3.getAsiento()).thenReturn(datos3);
        
        
        List<AsientoVueloFullData> lista = Arrays.asList(as2,as1,as3);
        OrdenPorPrecioAscendente ordenada = new OrdenPorPrecioAscendente();
        ordenada.ordenar(lista,null);
        MatcherAssert.assertThat(lista,IsIterableContainingInOrder.contains(as1,as3,as2));
    }

    
    
}
