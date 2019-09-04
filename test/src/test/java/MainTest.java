import org.junit.Test;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    public void doItExample() {
        String[] input = new String[] {"1","2","88","42","99"};
        String[] expectedOutput = new String[] {"1","2","88"};

        final String inputString = String.join(LINE_SEPARATOR, input)+ LINE_SEPARATOR;
        final String expectedOutputString = String.join(LINE_SEPARATOR, expectedOutput)+ LINE_SEPARATOR;
        assertEquals("Exemplo do problema", expectedOutputString, doIt(inputString, LINE_SEPARATOR));
    }

    @Test
    public void doItZeroItems() {
        final String inputString = "";
        final String expectedOutputString = "";
        assertEquals("Input em branco", expectedOutputString, doIt(inputString, LINE_SEPARATOR));
    }

    @Test
    public void doItPrimeiroNumeroEh42() {
        String[] input = new String[] {"42","1","2","88","99"};

        final String inputString = String.join(LINE_SEPARATOR, input)+ LINE_SEPARATOR;
        final String expectedOutputString = "";
        assertEquals("Primeiro numero eh 42", expectedOutputString, doIt(inputString, LINE_SEPARATOR));
    }

    @Test
    public void doItUltimoNumeroEh42() {
        String[] input = new String[] {"1","2","88","99","42"};
        String[] expectedOutput = new String[] {"1","2","88","99"};

        final String inputString = String.join(LINE_SEPARATOR, input)+ LINE_SEPARATOR;
        final String expectedOutputString = String.join(LINE_SEPARATOR, expectedOutput)+ LINE_SEPARATOR;
        assertEquals("Ultimo numero eh 42", expectedOutputString, doIt(inputString, LINE_SEPARATOR));
    }

    @Test
    public void doIt2000000numerosAntesDo42() throws IOException, URISyntaxException {
        final String inputString = readFixture("fixture.2000000.txt");
        final String expectedOutputString = readFixture("fixture.2000000-output.txt");
        long start = System.currentTimeMillis();
        final String actualOutput = doIt(inputString, "\n");
        long end   = System.currentTimeMillis();
        assertEquals("Teste de carga (2000000 antes do 42)", expectedOutputString, actualOutput);
        assertTrue("Tempo limite < 10 segundos (2000000 antes do 42)", end - start <= 10*1000);
    }

    /* CONVENIENCE METHODS BELLOW */

    private String readFixture(String fixtureFilename) throws URISyntaxException, IOException {
        URL fixtureURL = Objects.requireNonNull(getClass().getClassLoader().getResource(fixtureFilename), "fixtureFilename not found: " + fixtureFilename);
        Path path = Paths.get(fixtureURL.toURI());
        Stream<String> lines = Files.lines(path);

        String data = lines.collect(Collectors.joining("\n")) + "\n";
        lines.close();

        return data;
    }

    private String doIt(String input, String delimiter) {
        String output = "";

        try (final ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes("UTF-8"));
             final ByteArrayOutputStream baos = new ByteArrayOutputStream();
             final PrintStream ps = new PrintStream(baos, true, "UTF-8")
        ) {
            Main.doIt(bais, ps, delimiter);
            output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // do nothing
        }

        return output;
    }
}