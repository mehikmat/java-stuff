package language;

import java.util.Arrays;

/**
 * Created by hdhamee on 6/13/16.
 */
public class Java8Lambdas {

    /**
     * - A lambda(also known as closures) expression is like a method.
     * - it provides a list of formal parameters and a body (which can be an expression or a block of code)
     *   expressed in terms of those parameters.
     * - They allow us to treat functionality as a method argument or code as a data.
     * - Lambdas can be passed as a function/method parameter.
     * - Java's anonymous classes are also a kind of closure as they can be passed as method parameter.
     *
     * - The basic syntax of a lambda is either:
     *     (parameters) -> expression
     *           OR
     *     (parameters) -> { statements; }
     */
    public static void main(String[] args) {

        String[] array = new String[]{"a", "b", "d"};
        Arrays.asList(array).forEach( e -> System.out.println( e ) );


        // To explicitly specify the type of parameter, by default compiler infers the type
        Arrays.asList( array ).forEach( ( String e ) -> { System.out.println( e ); } );


        //Lambdas may reference the class members and local variables (implicitly making them effectively final if they are not).
        String separator = ",";
        Arrays.asList(array).forEach((String e ) -> System.out.print( e + separator ) );


        // Lambdas may return a value. The type of the return value will be inferred by compiler.
        // The return statement is not required if the lambda body is just a one-liner.
        Arrays.asList( array ).sort( ( e1, e2 ) -> {
            int result = e1.compareTo( e2 );
            return result;
        } );


    }
}
