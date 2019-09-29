package co.tiagoj.codechef.utils;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Fixtures {

    public static final String LINE_SEPARATOR = System.lineSeparator();

    /**
     * Converte uma string de tokens separados por espaco em uma string de tokens separados por LINE_SEPARATOR
     * @param input
     * @return
     */
    public static String generateFixture(String input) {
        Objects.requireNonNull(input, "input is null");
        return String.join(LINE_SEPARATOR, input.split(" "))+ LINE_SEPARATOR;
    }

    /**
     * Le a string de INPUT a partir de um arquivo no classpath
     * @param fixtureFilename path relativo do arquivo no classpath
     * @return
     * @throws URISyntaxException
     * @throws IOException
     */
    public static String readFixture(String fixtureFilename) throws URISyntaxException, IOException {
        URL fixtureURL = Fixtures.class.getClassLoader().getResource(fixtureFilename);
        Objects.requireNonNull(fixtureURL, "fixtureFilename not found: " + fixtureFilename);

        Path path = Paths.get(fixtureURL.toURI());
        Stream<String> lines = Files.lines(path);

        String data = lines.collect(Collectors.joining("\n")) + "\n";
        lines.close();

        return data;
    }

    /**
     *
     * @param input
     * @param delimiter
     * @param bc
     * @return
     */
    public static String prepareAndConsume(String input, String delimiter, BiConsumer<InputStream, PrintStream> bc) {
        String output = "";

        try (final ByteArrayInputStream bais = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
             final ByteArrayOutputStream baos = new ByteArrayOutputStream();
             final PrintStream ps = new PrintStream(baos, true, StandardCharsets.UTF_8.displayName())
        ) {
            bc.accept(bais, ps);
            output = new String(baos.toByteArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            // do nothing
            e.printStackTrace();
        }

        return output;
    }
}
