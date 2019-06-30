/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataDummy;

import aterrizarv2.AterrizarV2;
import aterrizarv2.aerolinea.Aerolinea;
import aterrizarv2.aerolinea.RequisitoCargaAsientos;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchita;
import aterrizarv2.aerolinea.aerolineaLanchita.AerolineaLanchitaI;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanic;
import aterrizarv2.aerolinea.aerolineaOceanic.AerolineaOceanicI;
import aterrizarv2.aerolinea.aerolineaOceanic.TipoPedidoInvalidaException;
import aterrizarv2.aerolinea.exceptionesAerolinea.DatosVueloIncorrectoException;
import aterrizarv2.asientos.excepcionesAsiento.AsientoReservadoException;
import aterrizarv2.asientos.excepcionesAsiento.CodigoAsientoException;
import aterrizarv2.fecha.FechaFlexible;
import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import aterrizarv2.hora.Hora;
import aterrizarv2.hora.excepcionesHora.FormatoHoraIncorrectoException;
import aterrizarv2.hora.excepcionesHora.HoraInvalidaException;
import aterrizarv2.usuarios.DniInvalidoException;
import aterrizarv2.usuarios.UsuarioPaga;
import aterrizarv2.vuelos.Vuelo;
import org.mockito.Mockito;

public class DataDummy {

    public  UsuarioPaga nuevoUsuario() throws DniInvalidoException, AsientoReservadoException{
        UsuarioPaga usuario = new UsuarioPaga("Juan Manuel", "Pardo", 1111111, 350);
        usuario.efectuarCompra(500000);
        AterrizarV2 aterrizar = this.setearAterrizarDummy();
        efectuarDosCompras(aterrizar,usuario);
        reservarUnAsiento(aterrizar,usuario);
        return usuario;
    }
    
    public AterrizarV2 paginaAerolineas() throws DniInvalidoException, FechaNoValidaException, FormatoFechaIncorrectoException, FormatoHoraIncorrectoException, DatosVueloIncorrectoException, HoraInvalidaException, TipoPedidoInvalidaException{
        AterrizarV2 aterrizar = this.setearAterrizarDummy();
        return aterrizar;
    }
            
    public AterrizarV2 setearAterrizarDummy() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, DatosVueloIncorrectoException, TipoPedidoInvalidaException, DniInvalidoException{
        Vuelo vueloRioLima;
        Vuelo vueloBsAsMadrid;
        AerolineaLanchita lanchitaNoMockeada;
        AerolineaLanchitaI lanchitaMockeada;
        FechaFlexible fechaSalida1Junio2018;
        FechaFlexible fechaSalida13Noviembre2018;
        FechaFlexible fechaLlegada2Junio2018;
        FechaFlexible fechaLlegada13Noviembre2018;
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
        
        
        String[][] asientosDisponiblesBueMad = {{"EC0344-42","565.60","P","P","D"}, {"EC0344-66","365.60","T","E","D"},{"EC0344-67","3265.60","T","E","D"},{"EC0344-44","565.60","P","P","D"}};
        String[][] asientosDisponiblesRioLim = {{"EC0LAM-12","4555.60","P","P","D"}, {"EC0LAM-13","3665.60","T","E","D"},
        {"EC0LAM-14","4555.60","P","P","D"},{"EC0LAM-15","4555.60","P","P","D"},{"EC0LAM-16","4555.60","P","P","D"},{"EC0LAM-17","4555.60","P","P","D"}};
        
        
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
    
        AterrizarV2 aterrizar = new AterrizarV2();      
        aterrizar.agregarAerolinea(lanchitaNoMockeada);
        aterrizar.agregarAerolinea(setearOceanicDummy());
        
        return aterrizar;
    }
    
    public static AerolineaOceanic setearOceanicDummy() throws FormatoFechaIncorrectoException, FechaNoValidaException, FormatoHoraIncorrectoException, HoraInvalidaException, TipoPedidoInvalidaException, DatosVueloIncorrectoException{
        Vuelo vueloRioLosAngeles;
        Vuelo vueloBsAsMadrid;
        AerolineaOceanic oceanic;
        AerolineaOceanicI oceanicMock;
        FechaFlexible fechaSalida1Junio2018;
        FechaFlexible fechaSalida13Noviembre2018;
        FechaFlexible fechaLlegada2Junio2018;
        FechaFlexible fechaLlegada13Noviembre2018;
    
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
        
        String[][] asientosBueLim = {{"LAM344","13",unoJunio,lasDoce,"565.60","P","P"},{"LAM344","23",unoJunio,lasDoce,"565.60","T","C"}};
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
        return oceanic;
    }
    
    
    public static void reservarUnAsiento(AterrizarV2 aterrizar, UsuarioPaga usuario) throws CodigoAsientoException, AsientoReservadoException{
        Aerolinea aerolinea = aterrizar.getAerolineas().get(0);
        aerolinea.reservarAsiento("EC0LAM-12", usuario);
    }
    
    public static void efectuarDosCompras(AterrizarV2 aterrizar, UsuarioPaga usuario) throws CodigoAsientoException, AsientoReservadoException{
        Aerolinea aerolinea = aterrizar.getAerolineas().get(0);
        aerolinea.comprarAsiento("EC0344-42", usuario);
        aerolinea.comprarAsiento("EC0344-66", usuario);
        Aerolinea aerolineaOc = aterrizar.getAerolineas().get(1);
        aerolineaOc.comprarAsiento("LAM344-13", usuario);
    }
}
