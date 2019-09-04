
import java.io.*;
import java.net.URISyntaxException;

import static co.tiagoj.codechef.utils.Fixtures.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestTest {
    private Test test;

    @org.junit.Before
    public void before() {
        this.test = new Test(LINE_SEPARATOR);
    }

    @org.junit.Test
    public void acceptExample() {
        final String inputString = generateFixture("1 2 88 42 99");
        final String expectedOutputString = generateFixture("1 2 88");
        assertEquals("Exemplo do problema", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, test));
    }

    @org.junit.Test
    public void acceptZeroItems() {
        final String inputString = "";
        final String expectedOutputString = "";
        assertEquals("Input em branco", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, test));
    }

    @org.junit.Test
    public void acceptPrimeiroNumeroEh42() {
        final String inputString = generateFixture("42 1 2 88 99");
        final String expectedOutputString = "";
        assertEquals("Primeiro numero eh 42", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, test));
    }

    @org.junit.Test
    public void acceptUltimoNumeroEh42() {
        final String inputString = generateFixture("1 2 88 99 42");
        final String expectedOutputString = generateFixture("1 2 88 99");
        assertEquals("Ultimo numero eh 42", expectedOutputString, prepareAndConsume(inputString, LINE_SEPARATOR, test));
    }

    @org.junit.Test
    public void acceptStressTempoLimite() throws IOException, URISyntaxException {
        final String inputString = readFixture("fixture.50000K.txt"); // 50.000 Bytes

        final long start = System.currentTimeMillis();
        final String actualOutput = prepareAndConsume(inputString, "\n", new Test("\n"));
        final long end   = System.currentTimeMillis();

        // 10 segundos
        final long elapsedTime = end - start;
        assertTrue("Tempo limite < 10 segundos estourado: " + elapsedTime/1000, elapsedTime <= 10*1000);

        // Saida esperada
        final String expectedOutputString = readFixture("fixture.50000K-output.txt");
        assertEquals("Teste de carga (20000000 antes do 42)", expectedOutputString, actualOutput);
    }
}