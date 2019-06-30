package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import junit.framework.Assert;
import org.junit.Test;


public class FechaFormatoNorteamericanoTest {
    
   @Test
    public void seCreaExitosamenteUnaFechaFormatoNorteamericanoYFuncionanGetters(){
        try {
            FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("05-28-2012");
            Assert.assertEquals("28", ffl.getDia());
            Assert.assertEquals("05", ffl.getMes());
            Assert.assertEquals("2012", ffl.getAnio());
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false, true);
        }     
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void lanzaExceptionYaQueElSeparadorEsIncorrecto() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("12/01/2012");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaNorteamConFormatoIncorrentoPorLongitudFechaExcesivaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("12/01/2012232");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaNorteamConFormatoIncorrentoPorqueEsIsoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("2012/12/13");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seIntentaCrearUnaFechaNorteamConFormatoIncorrentoPorContenidoNoNumericoYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("12/01/2j12");
    }
    
    @Test(expected = FechaNoValidaException.class)
    public void seIntentaCrearUnaFechaNorteamNoValidaYLanzaExcepcion() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFormatoNorteamericano ffl = new FechaFormatoNorteamericano("13-01-2012");
    }
}
