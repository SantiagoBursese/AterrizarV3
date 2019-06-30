package aterrizarv2.aerolinea.aerolineaOceanic;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.RequisitoCargaAsientos;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.ClaseAsiento;
import aterrizarv2.asientos.UbicacionAsiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
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
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.AsientoDTO;
import aterrizarv2.vuelos.Vuelo;
import java.util.LinkedList;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.mockito.Mockito;


public class aerolineaOceanicTest {
    Vuelo vueloRioLosAngeles;
    Vuelo vueloBsAsMadrid;
    AerolineaOceanic oceanic;
    AerolineaOceanicI oceanicMock;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    FechaFlexible fechaSalida1Junio2018;
    FechaFlexible fechaSalida13Noviembre2018;
    FechaFlexible fechaLlegada2Junio2018;
    FechaFlexible fechaLlegada13Noviembre2018;
    AterrizarV2 aterrizar;
   
    
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, TipoPedidoInvalidaException, DatosVueloIncorrectoException, DniInvalidoException {
        oceanicMock = Mockito.mock(AerolineaOceanicI.class);
        oceanic = new AerolineaOceanic(oceanicMock);
        
        
        /* CIUDADES */
        
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLosAngeles = "LA";
        
        String bueOceanic = oceanic.convertirFormatoCiudad(origenBuenosAires);
        String madOceanic = oceanic.convertirFormatoCiudad(destinoMadrid);
        String rioOceanic = oceanic.convertirFormatoCiudad(origenRioJaneiro);
        String losAngelesOceanic = oceanic.convertirFormatoCiudad(destinoLosAngeles);
        
        /* ----------------------------------------------------------------------------------------------*/
        
        
        
        
        /* FECHAS */
        
        
        fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
        fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
        
        
        String unoJunio = fechaSalida1Junio2018.representacionEnLatinoamericano();
        String dosJunio = fechaLlegada2Junio2018.representacionEnLatinoamericano();
        String treceNoviembre = fechaSalida13Noviembre2018.representacionEnLatinoamericano();
        
        
        /* ----------------------------------------------------------------------------------------------*/
        
        
        
        
        /* HORA */
        
        Hora horaSalida23hs = new Hora("23:00");
        Hora horaLlegada11hs = new Hora("11:00");
        Hora horaSalida12hs = new Hora("12:00");
        Hora horaLlegada00hs= new Hora("00:00");
        
        
        String lasOnce = "11:00";
        String lasVeintiTres = "23:00";
        String lasDoce = "12:00";
        String lasCeroCero = "00:00";
        
         /* ----------------------------------------------------------------------------------------------*/
        
        
        
        /* COMUNICACION CON OCEANICI MOCKEADA */
        
        String[][] asientosBueLim = {{"EC0344","13",unoJunio,lasDoce,"565.60","P","P"},{"EC0344","23",unoJunio,lasDoce,"565.60","T","C"}};
        String[][] asientosRioLA = {{"MLR123","67", treceNoviembre, lasVeintiTres, "872.50", "P", "P"},{"MLR123", "122", treceNoviembre, lasVeintiTres, "921.76", "T","C"}};
        
        
        Mockito.when(oceanicMock.asientosDisponiblesParaOrigen(bueOceanic, unoJunio)).thenReturn(asientosBueLim);
        Mockito.when(oceanicMock.asientosDisponiblesParaOrigenYDestino(bueOceanic, unoJunio,madOceanic)).thenReturn(asientosBueLim);
        
        Mockito.when(oceanicMock.asientosDisponiblesParaOrigen(rioOceanic, treceNoviembre)).thenReturn(asientosRioLA);
        Mockito.when(oceanicMock.asientosDisponiblesParaOrigenYDestino(rioOceanic, treceNoviembre, losAngelesOceanic)).thenReturn(asientosRioLA);
        
        /* ----------------------------------------------------------------------------------------------*/
        
        
        
        
        /* CREACION Y CARGA DE LOS VUELOS */
        
        vueloBsAsMadrid = new Vuelo(bueOceanic, madOceanic, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida12hs, horaLlegada11hs);
        vueloRioLosAngeles = new Vuelo(rioOceanic, losAngelesOceanic, fechaSalida13Noviembre2018, fechaLlegada13Noviembre2018, horaSalida23hs, horaLlegada00hs);
        
        RequisitoCargaAsientos requisitoOrigen = new RequisitoCargaAsientos("Origen");
        
        
        oceanic.agregarVuelo(vueloBsAsMadrid, requisitoOrigen);
        oceanic.agregarVuelo(vueloRioLosAngeles, requisitoOrigen);
        /* ----------------------------------------------------------------------------------------------*/
        
        
        
        
        /* Agregamos usuarios y aerolinea */
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        aterrizar.agregarAerolinea(oceanic);
        
        /* ----------------------------------------------------------------------------------------------*/
        
    }
    
    @Test
    public void convertirFormatoCiudad_SeIngresaUnaCiudadDeDosCaracteresYSeModificaCorrectamente() {
        String codigoCiudadOceanic = oceanic.convertirFormatoCiudad("LR");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "LR_", codigoCiudadOceanic);
    }
    
    @Test
    public void convertirFormatoCiudad_SeIngresaLaCiudadLAYSeModificaCorrectamente(){
        String codigoCiudadOceanic = oceanic.convertirFormatoCiudad("LA");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "SLA", codigoCiudadOceanic); 
    }
    
    @Test
    public void convertirFormatoCiudad_SeIngresaCiudadDeTresCaracteresYNoSeModifica(){
        String codigoCiudadOceanic = oceanic.convertirFormatoCiudad("BUE");
        assertEquals("El codigo de ciudad no esta en formato oceanic", "BUE", codigoCiudadOceanic); 
    }
    
    @Test
    public void asientosDisponiblesParaOrigenDevuelveAsientoDTOCorrectamente() throws TipoPedidoInvalidaException, CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException{
        String unoJunio = fechaSalida1Junio2018.representacionEnLatinoamericano();
        String dosJunio = fechaLlegada2Junio2018.representacionEnLatinoamericano();
        String treceNoviembre = fechaSalida13Noviembre2018.representacionEnLatinoamericano();
        String lasOnce = "11:00";
        String lasVeintiTres = "23:00";
        String lasDoce = "12:00";
        String lasCeroCero = "00:00";
        
        String[][] asientosBueLim = {{"EC0344","13",unoJunio,lasDoce,"565.60","P","P"},{"EC0344","23",unoJunio,lasDoce,"565.60","T","C"}};
        
        LinkedList<Asiento> asientosDTO = oceanic.devolverAsiento(asientosBueLim);
        
        ClaseAsiento primeraClase = new ClaseAsiento("P");
        ClaseAsiento turista = new ClaseAsiento("T");

        UbicacionAsiento pasillo = new UbicacionAsiento("P");
        UbicacionAsiento centro = new UbicacionAsiento("C");
        
        //{"EC0344","13",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","P","P"}
        //{"EC0324","23",fechaSalida13Noviembre2018,horaSalida12hs,"565.60","T","C"}
        AsientoDTO asientoCasteado1 = (AsientoDTO)asientosDTO.get(0);
        AsientoDTO asientoCasteado2 = (AsientoDTO)asientosDTO.get(1);
        
        Assert.assertEquals(asientoCasteado1.getFechaSalidaDTO().representacionEnLatinoamericano(),unoJunio );
        Assert.assertEquals(asientoCasteado2.getFechaSalidaDTO().representacionEnLatinoamericano(),unoJunio );
        
        Assert.assertEquals(asientoCasteado1.getHoraSalida().getHoraFormatoString(),lasDoce);
        Assert.assertEquals(asientoCasteado2.getHoraSalida().getHoraFormatoString(),lasDoce);
        

        Assert.assertEquals("EC0344", asientoCasteado1.getCodigo().getNumeroVuelo());
        Assert.assertEquals("EC0344", asientoCasteado2.getCodigo().getNumeroVuelo());
        
        Assert.assertEquals("13", asientoCasteado1.getCodigo().getNumeroAsiento());
        Assert.assertEquals("23", asientoCasteado2.getCodigo().getNumeroAsiento());
                
        Assert.assertEquals(565.60, asientoCasteado1.getPrecio().getPrecioAsiento());
        Assert.assertEquals(565.60, asientoCasteado2.getPrecio().getPrecioAsiento());

        
        Assert.assertEquals(asientoCasteado1.getClase().getClaseAsiento(),primeraClase.getClaseAsiento() );
        Assert.assertEquals(asientoCasteado2.getClase().getClaseAsiento(), turista.getClaseAsiento() );
        
        Assert.assertEquals(asientoCasteado1.getUbicacion().getUbicacionAsiento(),pasillo.getUbicacionAsiento() );
        Assert.assertEquals(asientoCasteado2.getUbicacion().getUbicacionAsiento(), centro.getUbicacionAsiento()); 
    }

    @Test
    public void asientosDisponiblesParaOrigenYDestinoDevuelveAsientoDTOCorrectamente() throws TipoPedidoInvalidaException, CodigoAsientoException, PrecioNegativoException, ClaseAsientoInvalidaException, UbicacionAsientoInvalidaException, EstadoAsientoInvalidaException, FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException{
        String unoJunio = fechaSalida1Junio2018.representacionEnLatinoamericano();
        String dosJunio = fechaLlegada2Junio2018.representacionEnLatinoamericano();
        String treceNoviembre = fechaSalida13Noviembre2018.representacionEnLatinoamericano();
        String lasOnce = "11:00";
        String lasVeintiTres = "23:00";
        String lasDoce = "12:00";
        String lasCeroCero = "00:00";
        
        String[][] asientosRioLA = {{"MLR123","67", treceNoviembre, lasVeintiTres, "872.50", "P", "P"},{"MLR123", "122", treceNoviembre, lasVeintiTres, "921.76", "T","C"}};
        
        LinkedList<Asiento> asientosDTO = oceanic.devolverAsiento(asientosRioLA);

        AsientoDTO asientoCasteado1 = (AsientoDTO)asientosDTO.get(0);
        AsientoDTO asientoCasteado2 = (AsientoDTO)asientosDTO.get(1);

        ClaseAsiento primeraClase = new ClaseAsiento("P");
        ClaseAsiento turista = new ClaseAsiento("T");

        UbicacionAsiento pasillo = new UbicacionAsiento("P");
        UbicacionAsiento centro = new UbicacionAsiento("C");

        //{"MLR123","67", fechaSalida1Junio2018, horaSalida23hs, "872.50", "P", "P"}
        //{"NDL113", "122", fechaSalida1Junio2018, horaSalida23hs, "921.76", "T","C"}


        Assert.assertEquals("MLR123", asientoCasteado1.getCodigo().getNumeroVuelo());
        Assert.assertEquals("MLR123", asientoCasteado2.getCodigo().getNumeroVuelo());

        Assert.assertEquals("67", asientoCasteado1.getCodigo().getNumeroAsiento());
        Assert.assertEquals("122", asientoCasteado2.getCodigo().getNumeroAsiento());

        Assert.assertEquals(asientoCasteado1.getFechaSalidaDTO().representacionEnLatinoamericano(),treceNoviembre );
        Assert.assertEquals(asientoCasteado2.getFechaSalidaDTO().representacionEnLatinoamericano(),treceNoviembre );

        Assert.assertEquals(asientoCasteado1.getHoraSalida().getHoraFormatoString(),lasVeintiTres);
        Assert.assertEquals(asientoCasteado2.getHoraSalida().getHoraFormatoString(),lasVeintiTres);

        Assert.assertEquals(872.50, asientoCasteado1.getPrecio().getPrecioAsiento());
        Assert.assertEquals(921.76, asientoCasteado2.getPrecio().getPrecioAsiento());


        Assert.assertEquals(asientoCasteado1.getClase().getClaseAsiento(),primeraClase.getClaseAsiento() );
        Assert.assertEquals(asientoCasteado2.getClase().getClaseAsiento(), turista.getClaseAsiento() );

        Assert.assertEquals(asientoCasteado1.getUbicacion().getUbicacionAsiento(),pasillo.getUbicacionAsiento() );
        Assert.assertEquals(asientoCasteado2.getUbicacion().getUbicacionAsiento(), centro.getUbicacionAsiento()); 
    }
    
    @Test
    public void seCompraUnAsientoSastifactoriamente() throws CodigoAsientoException, AsientoReservadoException{
        oceanic.comprarAsiento("EC0344-13", userEstandar);
        org.junit.Assert.assertEquals("565.6", String.valueOf(userEstandar.getPerfil().dineroGastado()));
        org.junit.Assert.assertEquals(oceanic.aerolineaTieneAsiento("EC0344-13"),true);
        Asiento asiento = oceanic.obtenerAsiento("EC0344-13").getAsiento();
        org.junit.Assert.assertEquals(asiento.getEstado().asientoVendido(),true);
    }
    
    @Test(expected = CodigoAsientoException.class)
    public void fallaCompraAsiento() throws CodigoAsientoException, AsientoReservadoException{
        oceanic.comprarAsiento("EC0344-422", userEstandar);
    }
   
}
