import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Java9Collections {

    public static void main(String[] args) {

        /**
         * List
         */

        // creating immutable list using factory method, List.of()
        List<String> list = List.of("Hikmat","Singh","Dhamee");
        System.out.println("From java9:" + list);

        // In java 8, for the same, we used to do
        List<String> list8 = new ArrayList<>();
        list8.add("Hikmat");
        list8 = Collections.unmodifiableList(list8);
        System.out.println("From java8: " + list8);



    }
}
