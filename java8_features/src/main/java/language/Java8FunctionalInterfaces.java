package language;

/**
 * - The function interface is an interface with just one single method.
 * - As such, it may be implicitly converted to a lambda expression.
 * - The java.lang.Runnable and java.util.concurrent.Callable are two great examples of functional interface
 *   and there are a lot more have been added to jdk 8.
 *
 * Created by hdhamee on 6/13/16.
 */
public class Java8FunctionalInterfaces {

    public static void main(String[] args) {
        //Implementing the functional interface by creating an anonymous inner class versus using lambda expression.
        carryOutWork(new ComplexFunctionalInterface() {
            @Override
            public void doWork() {
                System.out.println("Do work in SimpleFun impl...");
            }
        });
        carryOutWork(() -> System.out.println("Do work in lambda exp impl..."));
        //OR
        SimpleFuncInterface simpleFuncInterface = () -> System.out.println("Another way to call lambda: Do work in lambda exp impl...");
        simpleFuncInterface.doWork();
    }




    public static void carryOutWork(SimpleFuncInterface sfi){
        sfi.doWork();
    }

    // Java 8 adds special annotation @FunctionalInterface to avoid from adding more methods to this interface.
    // The interface can also declare the abstract methods from the java.lang.Object class,
    // but still the interface can be called as a Functional Interface.
    @FunctionalInterface
    public interface SimpleFuncInterface {
        public void doWork();
        public String toString();
        public boolean equals(Object o);
    }

    // Default and static methods do not break the functional interface contract and may be declared.
    @FunctionalInterface
    public interface ComplexFunctionalInterface extends SimpleFuncInterface {

        default public void doSomeWork(){
            System.out.println("Doing some work in interface impl...");
        }

        default public void doSomeOtherWork(){
            System.out.println("Doing some other work in interface impl...");
        }

        static void doTest(){
            System.out.println("Static method...");
        }
    }
}
