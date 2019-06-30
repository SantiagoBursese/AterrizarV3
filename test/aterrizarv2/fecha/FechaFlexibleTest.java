package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import org.junit.Assert;
import org.junit.Test;

public class FechaFlexibleTest {
    
    public FechaFlexibleTest() {
    }
    
    @Test
    public void seCreaFechaFlexibleFormatoLatamYFuncionanGetters() throws FormatoFechaIncorrectoException, FormatoFechaIncorrectoException, FormatoFechaIncorrectoException{
        try {
            FechaFlexible fx = new FechaFlexible("15/06/2018");
            Assert.assertEquals("15",fx.getDia());
            Assert.assertEquals("06",fx.getMes());
            Assert.assertEquals("2018",fx.getAnio());
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }   
    }
    
    
    @Test
    public void seCreaFechaFlexibleFormatoIsoYFuncionanGetters(){
        try {
            FechaFlexible fx = new FechaFlexible("2018-06-15");
            Assert.assertEquals("15",fx.getDia());
            Assert.assertEquals("06",fx.getMes());
            Assert.assertEquals("2018",fx.getAnio());
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }   
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seCreaFechaFlexibleFormatoIncorrectoYLanzaException() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFlexible fx = new FechaFlexible("15//6/2018");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void seCreaFechaFlexibleValorIncorrectoYLanzaException() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFlexible fx = new FechaFlexible("15/16/2018");
    }
    
    @Test(expected = FormatoFechaIncorrectoException.class)
    public void exceptionPorFalloSeparadores() throws FormatoFechaIncorrectoException, FechaNoValidaException{
        FechaFlexible fx = new FechaFlexible("10/16/2018");
    }
    
}