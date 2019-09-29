import java.io.*;
import java.net.URISyntaxException;

import static co.tiagoj.codechef.utils.Fixtures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FctrlTest {
    private Fctrl fctrl;

    @org.junit.Before
    public void before() {
        this.fctrl = new Fctrl(LINE_SEPARATOR);
    }

    @org.junit.Test
    public void acceptExample() {
        final String inputString = generateFixture("6 3 60 100 1024 23456 8735373");
        final String expectedOutputString = generateFixture("0 14 24 253 5861 2183837");
        assertEquals("Exemplo do problema", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, fctrl));
    }

    @org.junit.Test
    public void acceptTest1() {
        final String inputString = generateFixture("1 1");
        final String expectedOutputString = generateFixture("0");
        assertEquals("Exemplo do problema", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, fctrl));
    }


    @org.junit.Test
    public void acceptTest1000000000() {
        final String inputString = generateFixture("1 1000000000");
        final String expectedOutputString = generateFixture("249999998");
        assertEquals("Exemplo do problema", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, fctrl));
    }

    @org.junit.Test
    public void acceptZeroItems() {
        final String inputString = "";
        final String expectedOutputString = "";
        assertEquals("Input em branco", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR,fctrl));
    }

    @org.junit.Test
    public void acceptStressTempoLimite() throws IOException, URISyntaxException {
        final String inputString = readFixture("fixture.50000K.txt"); // 50.000 Bytes

        final long start = System.currentTimeMillis();
        final String actualOutput = prepareAndConsume(inputString, "\n", new Fctrl("\n"));
        final long end   = System.currentTimeMillis();

        // 8 segundos
        final long elapsedTime = end - start;
        assertTrue("Tempo limite < 16 segundos estourado: " + elapsedTime/1000, elapsedTime <= 16*1000);

        // Saida esperada
//        final String expectedOutputString = readFixture("fixture.50000K-output.txt");
//        assertEquals("Teste de carga (20000000 antes do 42)", expectedOutputString, actualOutput);
    }

    @org.junit.Test
    public void factorsOfFiveTest() {
        assertEquals("1: 1^1 => 0", 0 , fctrl.factorsOfFive(1));
        assertEquals("2: 2^1 => 0", 0 , fctrl.factorsOfFive(2));
        assertEquals("3: 3^1 => 0", 0 , fctrl.factorsOfFive(3));
        assertEquals("4: 2^2 => 0", 0 , fctrl.factorsOfFive(4));
        assertEquals("5: 5^1 => 1", 1 , fctrl.factorsOfFive(5));
        assertEquals("6: 2^1 * 3^1 => 0", 0 , fctrl.factorsOfFive(6));
        assertEquals("7: 7^1 => 0", 0 , fctrl.factorsOfFive(7));
        assertEquals("8: 2^3 => 0", 0 , fctrl.factorsOfFive(8));
        assertEquals("9: 3^2 => 0", 0 , fctrl.factorsOfFive(9));
        assertEquals("10: 2^1 * 5^1 => 1", 1 , fctrl.factorsOfFive(10));
        assertEquals("25: 5^1 => 2", 2 , fctrl.factorsOfFive(25));
        assertEquals("10: 2^2 * 5^2 => 2", 2 , fctrl.factorsOfFive(100));
        assertEquals("125: 5^3 => 3", 3 , fctrl.factorsOfFive(125));
        assertEquals("625: 5^4 => 4", 4 , fctrl.factorsOfFive(625));
        assertEquals("244140625: 5^12 => 12", 12 , fctrl.factorsOfFive(244140625));
        assertEquals("244140626: ? => ?", 0 , fctrl.factorsOfFive(244140626));
    }

    @org.junit.Test
    public void resolveZTest() {
        assertEquals("Z(1) deveria resultar 0", 0 , fctrl.z(1));
        assertEquals("Z(2) deveria resultar 0", 0 , fctrl.z(2));
        assertEquals("Z(3) deveria resultar 0", 0 , fctrl.z(3));
        assertEquals("Z(4) deveria resultar 0", 0 , fctrl.z(4));
        assertEquals("Z(5) deveria resultar 1", 1 , fctrl.z(5));
        assertEquals("Z(10) deveria resultar 2", 2 , fctrl.z(10));
        assertEquals("Z(15) deveria resultar 3", 3 , fctrl.z(15));
        assertEquals("Z(60) deveria resultar 14", 14 , fctrl.z(60));
        assertEquals("Z(1000) deveri resultar 249", 249  , fctrl.z(1000));
    }
}