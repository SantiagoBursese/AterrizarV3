package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import junit.framework.Assert;
import org.junit.Test;

public class FechaFormatoIsoTest {
    
    public FechaFormatoIsoTest() {
    }
    
    public void seCreaExitosamenteUnaFechaFormatoIsoYFuncionanGetters(){
        try {
            FechaFormatoIso ffl = new FechaFormatoIso("2012-05-28");
            Assert.assertEquals("28", ffl.getDia());
            Assert.assertEquals("05", ffl.getMes());
            Assert.assertEquals("2012", ffl.getAnio());
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false, true);
        }     
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void lanzaExceptionYaQueElSeparadorEsIncorrecto() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoIso ffl = new FechaFormatoIso("2012/05/28");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaIsoConFormatoIncorrentoPorLongitudFechaExcesivaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoIso ffl = new FechaFormatoIso("12/01/2012232");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaIsoConFormatoIncorrentoPorqueEsIsoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoIso ffl = new FechaFormatoIso("20/12/1302");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaIsoConFormatoIncorrentoPorContenidoNoNumericoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoIso ffl = new FechaFormatoIso("12/01/2j12");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaIsoNoValidaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoIso ffl = new FechaFormatoIso("2012/13/12");
    }
}