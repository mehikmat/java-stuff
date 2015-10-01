import org.codehaus.janino.CompileException;
import org.codehaus.janino.ExpressionEvaluator;
import org.codehaus.janino.Parser;
import org.codehaus.janino.Scanner;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class JaninoExample {

    public static void main(String[] args) throws InvocationTargetException,
            CompileException,Parser.ParseException, Scanner.ScanException {
        expressionTest();
    }

    // Expression Examples
    public static void expressionTest()throws InvocationTargetException,
            CompileException,Parser.ParseException, Scanner.ScanException{
        String expression = "c > d ? c : d"; // can be read from a static file at run time

        // Compile the expression once; relatively slow.
        ExpressionEvaluator ee = new ExpressionEvaluator(
                expression,                       // expression
                int.class,                        // expressionType
                new String[]{"c", "d"},           // parameterNames
                new Class[]{int.class, int.class} // parameterTypes, can be set dynamically
        );

        // Evaluate it with varying parameter values; very fast.
        Integer res = (Integer) ee.evaluate(
                new Object[]{          // parameterValues
                        new Integer(10),
                        new Integer(11),
                }
        );
        System.out.println("res = " + res);
    }


}
