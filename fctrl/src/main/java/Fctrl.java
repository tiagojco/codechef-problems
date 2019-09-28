import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.function.BiConsumer;

/**
 * The most important part of a GSM network is so called Base Transceiver Station (BTS). These transceivers form the
 * areas called cells (this term gave the name to the cellular phone) and every phone connects to the BTS with the
 * strongest signal (in a little simplified view). Of course, BTSes need some attention and technicians need to check
 * their function periodically. <br/>
 * <br/>
 * The technicians faced a very interesting problem recently. Given a set of BTSes to visit, they needed to find the
 * shortest path to visit all of the given points and return back to the central company building. Programmers have
 * spent several months studying this problem but with no results. They were unable to find the solution fast enough.
 * After a long time, one of the programmers found this problem in a conference article. Unfortunately, he found that
 * the problem is so called "Traveling Salesman Problem" and it is very hard to solve. If we have N BTSes to be visited,
 * we can visit them in any order, giving us N! possibilities to examine. The function expressing that number is called
 * factorial and can be computed as a product 1.2.3.4....N. The number is very high even for a relatively small N.<br/>
 * <br/>
 * The programmers understood they had no chance to solve the problem. But because they have already received the
 * research grant from the government, they needed to continue with their studies and produce at least some results.
 * So they started to study behavior of the factorial function.<br/>
 * <br/>
 * For example, they defined the function Z. For any positive integer N, Z(N) is the number of zeros at the end of the
 * decimal form of number N!. They noticed that this function never decreases. If we have two numbers N1<N2, then Z(N1)
 * <= Z(N2). It is because we can never "lose" any trailing zero by multiplying by any positive number. We can only get
 * new and new zeros. The function Z is very interesting, so we need a computer program that can determine its value
 * efficiently.<br/>
 * <br/>
 * <b>Input</b><br/>
 * There is a single positive integer T on the first line of input (equal to about 100000). It stands for the number of
 * numbers to follow. Then there are T lines, each containing exactly one positive integer number N,
 * 1 <= N <= 1000000000.<br/>
 * <br/>
 * <b>Output</b><br/>
 * For every number N, output a single line containing the single non-negative integer Z(N).<br/>
 * <br/>
 * @see <a href="https://www.codechef.com/problems/FCTRL">FCTRL problem</a>
 *
 */
class Fctrl implements BiConsumer<InputStream, PrintStream> {

    private int[] inputSet;
//    private Map<Integer,Integer> inputSet = new LinkedHashMap<>();
    private int[] cache;
    private final String delimiter;

    public static void main(String[] args) {
        new Fctrl(System.lineSeparator()).accept(System.in, System.out);
    }

    /**
     *
     * @param delimiter CRLF, LF, CR
     */
    Fctrl(String delimiter) {
        this.delimiter = delimiter;
    }
    /**
     * The actual implementation. Three Loops:
     *  - The first one fills the input set, and detect the biggestInputedNumber value;
     *  - The second will resolve Z for the biggestInputedNumber value (and cache the intermediary results);
     *  - The third one, will iterate through the input set to print the results ;
     *
     * @param in Example: <pre>6 3 60 100 1024 23456 8735373</pre>
     * @param out Example: <pre>0 14 24 253 5861 2183837</pre>
     */
    @Override
    public void accept(InputStream in, PrintStream out) {
        final Scanner s = new Scanner(in);

        if (!s.hasNext()) {
            return;
        }

        int inputSize = s.nextInt();
        int biggestInputedNumber = -1;

        // INPUT READ AND DETECT MAX VALUE
        inputSet = new int[inputSize];
        for (int i = 0; i < inputSize; i++) {
            int inputedNumber = s.nextInt();

            if (inputedNumber > biggestInputedNumber) {
                biggestInputedNumber = inputedNumber;
            }

            inputSet[i] = inputedNumber;
        }

        // THEN WE'LL RESOLVE Z FOR biggestInputedNumber
        z(biggestInputedNumber);

        // PRINT THE RESULTS
        for (int x : inputSet) {
            out.print(cache[x]+delimiter);
        }

        // CLEANUP
        if (in != System.in) {
            s.close(); // this also closes then 'in' parameter
        }

        if (out != System.out) {
            out.close();
        }
    }

    int factorsOfFive(int n) {
        int result = 0;

        // here is the magic...
        for (int powerOfFive = 1; n % Math.pow(5, powerOfFive) == 0; powerOfFive++) {
            result++;
        }

        return result;
    }

    /**
     * Memoization
     * @param n
     */
    public int z(int n) {
        // INIT THE CACHE
        cache = new int[n+1];

        if (n < 5) {
            return 0; // already solved
        }

        for (int i = 5; i <= n; i++) {
            int aux = cache[i-1] + factorsOfFive(i);

            // AND THEN, CACHE IT
            cache[i] = aux;
        }

        return cache[n];
    }
}
