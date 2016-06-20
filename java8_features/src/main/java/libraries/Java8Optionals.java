package libraries;

import java.util.Optional;

/**
 * Created by hdhamee on 6/15/16.
 */
public class Java8Optionals {

    /**
     * Long time ago the great Google Guava project introduced the Optionals as a solution to NullPointerExceptions,
     * discouraging codebase pollution with null checks and encouraging developers to write cleaner code.
     *
     * Inspired by Google Guava, the Optional is now a part of Java 8 library.
     *
     * Optional is just a container: it can hold a value of some type T or just be null.
     * It provides a lot of useful methods so the explicit null checks have no excuse anymore.
     *
     */
    public static void main(String[] args) {
        //The map() method transforms the current Optional’s value and returns the new Optional instance.
        // The orElse() method is similar to orElseGet() but instead of function it accepts the default value.
        Optional<String> fullName = Optional.ofNullable(null);
        System.out.println( "Full Name is set? " + fullName.isPresent() );
        System.out.println( "Full Name: " + fullName.orElseGet(() -> "[none]"));

        //The map() method transforms the current Optional’s value and returns the new Optional instance.
        // The orElse() method is similar to orElseGet() but instead of function it accepts the default value.
        System.out.println( fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");
        Optional<String> fullName1 = Optional.of("Hikmat Dhamee");
        System.out.println( "Full Name is set? " + fullName1.isPresent() );
        System.out.println( "Full Name: " + fullName1.orElseGet(() -> "[none]"));
        System.out.println( fullName1.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }

}
