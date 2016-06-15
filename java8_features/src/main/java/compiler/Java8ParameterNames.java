package compiler;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * Created by hdhamee on 6/15/16.
 */
public class Java8ParameterNames {
    // Method parameter names can be preserved for getting at run time using compiler
    // argument –parameters
    // this parameter can be set in maven compiler plugin
    public static void main(String[] args) throws Exception {
        Method method = Java8ParameterNames.class.getMethod( "main", String[].class );
        for( final Parameter parameter: method.getParameters() ) {
            System.out.println( "Parameter: " + parameter.getName() );
        }
    }

    // this will print parameter names like args ... but in java 7,it's arg0,arg1
}
