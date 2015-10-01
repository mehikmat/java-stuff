import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Java8FeatureTest {

    public static void main(String[] args) {
          lambdaTest();
    }

    //  A lambda(also known as closures) expression is like a method,
    //  it provides a list of formal parameters and a body
    //  (which can be an expression or a block of code) expressed in terms of those parameters.
    //  The basic syntax of a lambda is either:
    //     (parameters) ->expression
    //             OR
    //     (parameters) ->{ statements; }
    public static void lambdaTest(){
        String[] atp = {"Rafael Nadal", "Novak Djokovic", "Stanislas Wawrinka", "David Ferrer", "Roger Federer", "Andy Murray", "Tomas Berdych", "Juan Martin Del Potro"};
        List<String> players =  Arrays.asList(atp);

        // Old looping
        for (String player : players) {
            System.out.print(player + " ^ ");
        }

        System.out.println();

        // Using lambda expression and functional operations
        players.forEach((player) -> System.out.print(player + " ~ "));

        System.out.println();

        // Using double colon operator in Java 8
        players.forEach(System.out::print);

        // Lambda for Collections
        // Sort players by name using lambda expression
        Comparator<String> sortByName = (String s1, String s2) -> (s1.compareTo(s2));

        Arrays.sort(atp, sortByName);
       // or this
        Arrays.sort(atp, (String s1, String s2) -> (s1.compareTo(s2)));

    }
    // Interface’s Default and Static Methods
    private interface Defaulable {
        // Interfaces now allow default methods, the implementer may or
        // may not implement (override) them.
        default String notRequired() {
            return "Default implementation";
        }
    }

    private static class DefaultableImpl implements Defaulable {
    }

    private static class OverridableImpl implements Defaulable {
        @Override
        public String notRequired() {
            return "Overridden implementation";
        }
    }

    // More New Feature


}
