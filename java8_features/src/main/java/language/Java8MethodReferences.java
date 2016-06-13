package language;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by hdhamee on 6/13/16.
 */
public class Java8MethodReferences {

    public static void  main(String[] args) {

        // Suppliers is a function interface and represents a function that accepts no arguments and produce
        // a result of some arbitrary type.

        //Supplier referencing a constructor method:
        Supplier<User> userSupplierConstructor = User::new;
        User user1 = userSupplierConstructor.get();

        //Supplier referencing a static method:
        Supplier<User> userSupplierStatic = UserFactory::produceUserStatic;
        User user2 = userSupplierStatic.get();

        //Supplier Referencing a instance method:
        UserFactory userFactory = new UserFactory();
        Supplier<User> userSupplierInstance = userFactory::produceUser;
        User user3 = userSupplierInstance.get();

        //Consumers represent a function that accepts a single argument of an arbitrary type and produce no result

        // consumer using lambda expression
        Consumer<User> userConsumerLambda = (u) -> System.out.println("Username: " + u.getUsername());
        userConsumerLambda.accept(user3);

        // consumer using method referencing
        Consumer<User> userConsumerMth = UserFactory::printName;
        userConsumerMth.accept(user3);




        //referencing a static method
        List<Double>  numbers = Arrays.asList(4.0, 9.0, 16.0, 25.0, 36.0);
        List<Double> squaredNumbers = Java8MethodReferences.findSquareRoot(numbers,Double::new);
        System.out.println("Square root of numbers = "+squaredNumbers);
    }

    private static List findSquareRoot(List list, Function<Double, Double> f){
        List<Double> result = new ArrayList<>();
        list.forEach( x -> result.add(f.apply(Math.sqrt((Double) x))));
        return result;
    }


    static class UserFactory {
        public  User produceUser() {
            return new User();
        }

        public static User produceUserStatic() {
            return new User();
        }

        public static void printName(User user){
            System.out.println(user.getUsername());
        }
    }

    static class User {
        public String getUsername(){
            return "hari ram";
        }
    }
}
