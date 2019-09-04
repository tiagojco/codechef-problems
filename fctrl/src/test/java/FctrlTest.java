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
        assertTrue("Tempo limite < 8 segundos estourado: " + elapsedTime/1000, elapsedTime <= 8*1000);

        // Saida esperada
//        final String expectedOutputString = readFixture("fixture.50000K-output.txt");
//        assertEquals("Teste de carga (20000000 antes do 42)", expectedOutputString, actualOutput);
    }
}