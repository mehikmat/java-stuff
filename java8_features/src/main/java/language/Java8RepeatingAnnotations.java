package language;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by hdhamee on 6/13/16.
 */
public class Java8RepeatingAnnotations {

    @Retention(RetentionPolicy.RUNTIME)
    public @interface Filters {
        Filter[] value();
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Repeatable(Filters.class )
    public @interface Filter {
        String value();
    };

    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {
    }

    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType(Filter.class)) {
            System.out.println( filter.value() );
        }
    }
}
