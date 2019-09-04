import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;
import java.util.function.BiConsumer;

/**
 * Your program is to use the brute-force approach in order to find the Answer to Life, the Universe, and Everything.
 * More precisely... rewrite small numbers from input to output. Stop processing input after reading in the number 42.
 * All numbers at input are integers of one or two digits.<br/>
 * <br/>
 * @see <a href="https://www.codechef.com/problems/TEST">TEST problem</a>
 */
class Test implements BiConsumer<InputStream, PrintStream> {

    private final String delimiter;

    public static void main(String[] args) {
        new Test(System.lineSeparator()).accept(System.in, System.out);
    }

    /**
     *
     * @param delimiter CRLF, LF, CR
     */
    Test(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * The actual implementation
     * @param in Example: <pre>1 2 88 42 99</pre>
     * @param out Example: <pre>1 2 88</pre>
     */
    public void accept(InputStream in, PrintStream out) {
        final Scanner s = new Scanner(in);

        while (s.hasNextInt()) {
            int i = s.nextInt();

            // ANSWER TO LIFE THE UNIVERSE AND EVERYTHING
            if (i == 42) {
                break;
            }

            out.print(i+delimiter);
        }

        if (in != System.in) {
            s.close(); // this also closes then 'in' parameter
        }

        if (out != System.out) {
            out.close();
        }
    }
}
