package aterrizarv2.fecha;

import aterrizarv2.fecha.excepcionesFecha.FechaNoValidaException;
import aterrizarv2.fecha.excepcionesFecha.FormatoFechaIncorrectoException;
import org.junit.Assert;
import org.junit.Test;

public class FechaTest {
    
    @Test
    public void pasaron20DiasFechaLatamConNorteam(){
        try {
            FechaFormatoNorteamericano ffn = new FechaFormatoNorteamericano("12-01-2012");
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("21/12/2012");
            Assert.assertEquals(20, Fecha.diasPasaron(ffl, ffn));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
    @Test
    public void pasaron365DiasFechaNorteamIso(){
        try {
            FechaFormatoNorteamericano ffn = new FechaFormatoNorteamericano("12-01-2013");
            FechaFormatoIso ffi = new FechaFormatoIso("2012-12-01");
            Assert.assertEquals(365, Fecha.diasPasaron(ffi, ffn));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
    @Test
    public void pasaron28DiasFechaLatamFlex(){
        try {
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("29/12/2012");
            FechaFlexible fx = new FechaFlexible("2012-12-01");
            Assert.assertEquals(28, Fecha.diasPasaron(ffl, fx));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
    @Test
    public void esMismaFechaDevuelve0(){
        try {
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("29/12/2012");
            FechaFlexible fx = new FechaFlexible("2012-12-29");
            Assert.assertEquals(0, Fecha.diasPasaron(ffl, fx));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
    @Test
    public void fechaLatamEsAnterior(){
        try {
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("29/12/2011");
            FechaFlexible fx = new FechaFlexible("2012-01-12");
            Assert.assertEquals(ffl, Fecha.fechaAnterior(ffl, fx));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
    @Test
    public void fechaIsoEsAnterior(){
        try {
            FechaFormatoLatinoamericano ffl = new FechaFormatoLatinoamericano("29/12/2011");
            FechaFormatoIso ffi = new FechaFormatoIso("2011-11-01");
            Assert.assertEquals(ffi, Fecha.fechaAnterior(ffl, ffi));
        } catch (FormatoFechaIncorrectoException | FechaNoValidaException ex) {
            Assert.assertEquals(false,true);
        }
    }
    
}
