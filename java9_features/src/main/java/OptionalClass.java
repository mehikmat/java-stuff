import java.util.Optional;

public class OptionalClass {

    public static void main(String[] args) {

        // Optional is just a container which holds a value of some type T
        Optional<String> fullName = Optional.of("Hikmat Dhamee");

        System.out.println( "Full Name is set? " + fullName.isPresent() );// null check
        System.out.println( "Full Name: " + fullName.orElseGet(() -> "Null Value")); // if null, get default
        System.out.println(fullName.map( s -> "Hey " + s + "!" ).orElse( "Hey Stranger!" ) );
    }
}
