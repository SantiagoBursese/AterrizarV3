package aterrizarv2.vuelos;

import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.CodigoAsiento;
import aterrizarv2.asientos.EnumClaseAsiento;
import aterrizarv2.asientos.EnumEstadoAsiento;
import aterrizarv2.asientos.EnumUbicacionAsiento;
import aterrizarv2.asientos.EstadoAsiento;
import aterrizarv2.asientos.PrecioAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.ClaseAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.asientos.excepcionesAsiento.EstadoAsientoInvalidaException;
import aterrizarv2.asientos.excepcionesAsiento.PrecioNegativoException;
import aterrizarv2.asientos.excepcionesAsiento.UbicacionAsientoInvalidaException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mockito;


public class VueloTest {
    
    public VueloTest() {
    }
    
    EnumClaseAsiento claseTurista = EnumClaseAsiento.TURISTA;
    EnumClaseAsiento clasePrimeraClase = EnumClaseAsiento.PRIMERA_CLASE;
    EnumClaseAsiento claseEjecutivo = EnumClaseAsiento.EJECUTIVO;
    EnumUbicacionAsiento ubicacionVentana = EnumUbicacionAsiento.VENTANA;
    EnumUbicacionAsiento ubicacionPasillo = EnumUbicacionAsiento.PASILLO;
    EnumUbicacionAsiento ubicacionCentro = EnumUbicacionAsiento.CENTRO;
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    Asiento asiento2;
    Asiento asiento1;
    AerolineaLanchita aerolinea;

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, CodigoAsientoException, PrecioNegativoException, EstadoAsientoInvalidaException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        FechaFlexible fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        FechaFlexible fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        FechaFlexible fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
        FechaFlexible fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
        Hora horaSalida23hs = new Hora("23:05");
        Hora horaLlegada11hs = new Hora("11:30");
        Hora horaSalida12hs = new Hora("12:20");
        Hora horaLlegada21hs = new Hora("21:15");
        
        
        aerolinea = Mockito.mock(AerolineaLanchita.class);
        
        
        vueloBsAsMadrid = new Vuelo(origenBuenosAires, destinoMadrid, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida23hs, horaLlegada11hs);
        
        String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"}};
        
        LinkedList<Asiento> asientosBueMad = new LinkedList<>();
        
        CodigoAsiento codigo = new CodigoAsiento(asientosDisponiblesBueMad[0][0]);
        PrecioAsiento precio = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueMad[0][1]));
        ClaseAsiento clase = new ClaseAsiento(asientosDisponiblesBueMad[0][2]);
        UbicacionAsiento ubicacion = new UbicacionAsiento(asientosDisponiblesBueMad[0][3]);
        EstadoAsiento estado = new EstadoAsiento(asientosDisponiblesBueMad[0][4]);
        Asiento asientoUno = new Asiento(clase, codigo, estado, precio, ubicacion);
        
        CodigoAsiento codigoDos = new CodigoAsiento(asientosDisponiblesBueMad[1][0]);
        PrecioAsiento precioDos = new PrecioAsiento(Double.parseDouble(asientosDisponiblesBueMad[1][1]));
        ClaseAsiento claseDos = new ClaseAsiento(asientosDisponiblesBueMad[1][2]);
        UbicacionAsiento ubicacionDos = new UbicacionAsiento(asientosDisponiblesBueMad[1][3]);
        EstadoAsiento estadoDos = new EstadoAsiento(asientosDisponiblesBueMad[1][4]);
        Asiento asientoDos = new Asiento(claseDos, codigoDos, estadoDos, precioDos, ubicacionDos);
        
        asientosBueMad.add(asientoUno);
        asientosBueMad.add(asientoDos);
        
        Mockito.when(aerolinea.devolverAsiento(asientosDisponiblesBueMad)).thenReturn(asientosBueMad);
        
        Mockito.when(aerolinea.asientosDisponibles(vueloBsAsMadrid,null)).thenReturn(asientosDisponiblesBueMad);

        
    }
    
    @Test
    public void agregamosVueloAerolinea() throws CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, TipoPedidoInvalidaException, FechaNoValidaException, FormatoFechaIncorrectoException, FormatoHoraIncorrectoException, HoraInvalidaException{
        vueloBsAsMadrid.cargarAsientos(aerolinea,null);
        LinkedList<Asiento> asientosVuelo = vueloBsAsMadrid.getAsientosVuelo();

        ClaseAsiento primeraClase = new ClaseAsiento("P");
        ClaseAsiento turista = new ClaseAsiento("T");

        UbicacionAsiento pasillo = new UbicacionAsiento("P");
        UbicacionAsiento ventana = new UbicacionAsiento("E");

        
        Assert.assertEquals("EC0344-42", asientosVuelo.get(0).getCodigo().getCodigo());
        Assert.assertEquals("EC0344-66", asientosVuelo.get(1).getCodigo().getCodigo());
        
        Assert.assertEquals(asientosVuelo.get(0).getClase().getClaseAsiento(),primeraClase.getClaseAsiento());
        Assert.assertEquals(asientosVuelo.get(1).getClase().getClaseAsiento(),turista.getClaseAsiento());
        
        Assert.assertEquals(565.60, asientosVuelo.get(0).getPrecio().getPrecioAsiento());
        Assert.assertEquals(365.60, asientosVuelo.get(1).getPrecio().getPrecioAsiento());
        
        Assert.assertEquals(asientosVuelo.get(0).getUbicacion().getUbicacionAsiento(),pasillo.getUbicacionAsiento());
        Assert.assertEquals(asientosVuelo.get(1).getUbicacion().getUbicacionAsiento(),ventana.getUbicacionAsiento());
        
        Assert.assertEquals(asientosVuelo.get(0).getEstado().getEstadoAsiento(),EnumEstadoAsiento.DISPONIBLE);
        Assert.assertEquals(asientosVuelo.get(1).getEstado().getEstadoAsiento(),EnumEstadoAsiento.DISPONIBLE);
        
    }
    
     
}

