import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Your program is to use the brute-force approach in order to find the Answer to Life, the Universe, and Everything.
 * More precisely... rewrite small numbers from input to output. Stop processing input after reading in the number 42.
 * All numbers at input are integers of one or two digits.
 *  @see <a href="https://www.codechef.com/problems/TEST">TEST problem</a>
 */
class Main {

    private static final int ANSWER_TO_LIFE_THE_UNIVERSE_AND_EVERYTHING = 42;

    public static void main(String[] args) {
        doIt(System.in, System.out, System.lineSeparator());
    }

    /**
     * The actual implementation
     * @param in Example: <pre>1 2 88 42 99</pre>
     * @param out Example: <pre>1 2 88</pre>
     * @param delimiter CRLF, LF, CR
     */
    static void doIt(InputStream in, PrintStream out, String delimiter) {
        final Scanner s = new Scanner(in);

        while (s.hasNextInt()) {
            int i = s.nextInt();
            if (i == ANSWER_TO_LIFE_THE_UNIVERSE_AND_EVERYTHING) {
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
