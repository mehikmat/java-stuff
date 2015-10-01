package test.annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Main {
    public static void main(String[] args) {
        Class<Animal> animalClass = Animal.class;

        // Retrieve all annotations from the class
        Annotation[] annotations = animalClass.getAnnotations();
        for (Annotation annotation : annotations){
            System.out.println("Annotation: " + annotation);
        }

        // check if particular annotation is present
        if( animalClass.isAnnotationPresent( Dog.class ) )
        {
            // Gets the desired annotation
            Dog dogged = animalClass.getAnnotation( Dog.class );
            if (dogged.type().equalsIgnoreCase("black"))
                System.out.println("This is black dog : " + dogged.name());
            else
                System.out.println("This is not black dog: " +  dogged.name());
        }

        // the same for all methods of the class
        for( Method method : animalClass.getDeclaredMethods() ){
            if( method.isAnnotationPresent( Dog.class ) ){
                Annotation annotation = method.getAnnotation( Dog.class );
                System.out.println( annotation );
            }
        }
    }
}
