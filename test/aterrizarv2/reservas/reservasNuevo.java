package aterrizarv2.reservas;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.Asiento;
import aterrizarv2.asientos.excepcionesAsiento.AsientoNoDisponibleException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioNoPaga;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.Vuelo;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;


public class reservasNuevo {
    Vuelo vueloRioLima;
    Vuelo vueloBsAsMadrid;
    AerolineaLanchita lanchitaNoMockeada;
    AerolineaLanchitaI lanchitaMockeada;
    UsuarioPaga userVip;
    UsuarioNoPaga userEstandar;
    FechaFlexible fechaSalida1Junio2018;
    FechaFlexible fechaSalida13Noviembre2018;
    FechaFlexible fechaLlegada2Junio2018;
    FechaFlexible fechaLlegada13Noviembre2018;
    AterrizarV2 aterrizar;
   
   
    @Before
    public void setUp() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, DatosVueloIncorrectoException, TipoPedidoInvalidaException, DniInvalidoException {
        String origenBuenosAires = "BUE";
        String destinoMadrid = "MAD";
        String origenRioJaneiro = "RIO";
        String destinoLima = "LIM";
        fechaSalida1Junio2018 = new FechaFlexible("01/06/2018");
        fechaLlegada2Junio2018 = new FechaFlexible("02/06/2018");
        fechaSalida13Noviembre2018 = new FechaFlexible("13/11/2018");
        fechaLlegada13Noviembre2018 = new FechaFlexible("13/11/2018");
        Hora horaSalida23hs = new Hora("23:05");
        Hora horaLlegada11hs = new Hora("11:30");
        Hora horaSalida12hs = new Hora("12:20");
        Hora horaLlegada21hs = new Hora("21:15");

        lanchitaMockeada = Mockito.mock(AerolineaLanchitaI.class);
        lanchitaNoMockeada = new AerolineaLanchita(lanchitaMockeada);
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        lanchitaMockeada = Mockito.mock(AerolineaLanchitaI.class);
        lanchitaNoMockeada = new AerolineaLanchita(lanchitaMockeada);
        
        String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"}};
        String[][] asientosDisponiblesRioLim = {{"EC0LAM-12","4555.60","P","P","D"}, {"EC0LAM-13","3665.60","T","E","D"}};
        
         Mockito.when(lanchitaMockeada.asientosDisponibles("BUE", "MAD", fechaSalida1Junio2018.representacionEnIso(), 
                fechaLlegada2Junio2018.representacionEnIso(), horaSalida23hs.getHoraFormatoString(), 
                horaLlegada11hs.getHoraFormatoString())).thenReturn(asientosDisponiblesBueMad);
        
        Mockito.when(lanchitaMockeada.asientosDisponibles("RIO", "LIM", fechaSalida13Noviembre2018.representacionEnIso(), 
                fechaLlegada13Noviembre2018.representacionEnIso(), horaSalida12hs.getHoraFormatoString(), 
                horaLlegada21hs.getHoraFormatoString())).thenReturn(asientosDisponiblesRioLim);

        vueloBsAsMadrid = new Vuelo(origenBuenosAires, destinoMadrid, fechaSalida1Junio2018, fechaLlegada2Junio2018, horaSalida23hs, horaLlegada11hs);
        vueloRioLima = new Vuelo(origenRioJaneiro, destinoLima, fechaSalida13Noviembre2018, fechaLlegada13Noviembre2018, horaSalida12hs, horaLlegada21hs);
        
        lanchitaNoMockeada.agregarVuelo(vueloBsAsMadrid, null);
        lanchitaNoMockeada.agregarVuelo(vueloRioLima, null);
    
        
        userVip = new UsuarioPaga("Juan", "Carlos",41565456 ,320 );
        userVip.efectuarCompra(200000);
        userEstandar = new UsuarioNoPaga("Pedro", "Benitez", 31256748);
        
        aterrizar = new AterrizarV2();
        aterrizar.agregarAerolinea(lanchitaNoMockeada);
         
    }
    
    @Test
    public void reservarAsiento_reservaExitosaDeUsuarioAUnAsiento() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void reservarAsiento_usuarioReservaVariosAsientosExitosamente() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        String codigoAsiento2 = "EC0344-66";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        Asiento asientoDos= lanchitaNoMockeada.obtenerAsiento(codigoAsiento2).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userVip.reservarAsiento(codigoAsiento2, lanchitaNoMockeada);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoDos)); 
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
        Assert.assertTrue("Estado asiento no es reservado",asientoDos.getEstado().estaReservado());
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoReservadoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test(expected = AsientoReservadoException.class)
    public void reservarAsiento_UsuarioNoPuedeComprarAsientoReservadoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test(expected = AsientoNoDisponibleException.class)
    public void reservarAsiento_UsuarioNoPuedeReservarAsientoCompradoPorOtro() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        userVip.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
    }
    
    @Test
    public void expiroReserva_UsuarioReservaPeroSuReservaExpiraYOtroUsuarioReservaSuAsiento() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        userEstandar.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno)); 
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void expiroReserva_UsuarioPierdeLaReservaYElAsientoEstaDisponible() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento es reservado",asientoUno.getEstado().estaDisponible());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioSobrereservaAsientoReservado() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        Assert.assertTrue("No reservo el asiento", userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertEquals("No sobrereservo el asiento", userEstandar, AterrizarV2.usuarioSobrereserva(codigoAsiento));
        Assert.assertTrue("Estado asiento no es sobrereservado",asientoUno.getEstado().estaSobrereservado());
    }
    
    @Test
    public void sobrereservarAsiento_UsuarioPierdeReservaYPasaAlUsuarioQueSobrereservo() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        lanchitaNoMockeada.expiroReserva(asientoUno, userVip);
        Assert.assertTrue("Esta en las reservas el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("No reservo el asiento", userEstandar.asientoReservadoPorMi(asientoUno));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().estaReservado());
    }
    
    @Test
    public void comprar_UsuarioCompraAsientoYElUsuarioQueSobrereservoLoPierde() throws CodigoAsientoException, AsientoReservadoException{
        String codigoAsiento = "EC0344-42";
        Asiento asientoUno = lanchitaNoMockeada.obtenerAsiento(codigoAsiento).getAsiento();
        userVip.reservarAsiento(codigoAsiento, lanchitaNoMockeada);
        aterrizar.sobrereservarAsiento(codigoAsiento, userEstandar);
        userVip.comprarAsiento(codigoAsiento, lanchitaNoMockeada);
        Assert.assertTrue("Esta reservado el asiento", !userVip.asientoReservadoPorMi(asientoUno));
        Assert.assertNotSame("Esta en sobrereservado por el usuario", userEstandar, AterrizarV2.usuarioSobrereserva(codigoAsiento));
        Assert.assertTrue("Estado asiento no es reservado",asientoUno.getEstado().asientoVendido());
    }
}
