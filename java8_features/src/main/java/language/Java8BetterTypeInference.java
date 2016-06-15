package language;

/**
 * Created by hdhamee on 6/15/16.
 */
public class Java8BetterTypeInference {
    /**
     * Java 8 compiler has improved a lot on type inference.
     * In many cases the explicit type parameters could be inferred by compiler keeping the code cleaner.
     *
     */
    public static void main(String[] args) {
        final Value< String > value = new Value<>();
        value.getOrDefault( "22", Value.defaultValue() );
    }


    public static class Value< T > {

        public static< T > T defaultValue() {
            return null;
        }

        public T getOrDefault( T value, T defaultValue ) {
            return ( value != null ) ? value : defaultValue;
        }
    }
    /**
     * The type parameter of Value.defaultValue()is inferred and is not required to be provided.
     * In Java 7, the same example will not compile and should be rewritten to Value.< String >defaultValue().
     */
}
