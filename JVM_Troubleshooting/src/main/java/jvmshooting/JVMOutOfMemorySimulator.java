package jvmshooting;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryPoolMXBean;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Hikmat Dhamee
 * @email hdhamee@deerwalk.com
 */
public class JVMOutOfMemorySimulator {

    public JVMOutOfMemorySimulator()
    {
    }

    public static void main(String args[])
    {
        System.out.println("JVM OutOfMemoryError Simulator 1.0");
        System.out.println("Author: Hikmat Dhamee");
        System.out.println("http://hikmatdhamee.com.np");
        try
        {
            for(int i = 0; i < 28672; i++)
            {
                String data = (new StringBuilder()).append("datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadat").append(i).toString();
                leakingMap.put(data, data);
            }

        }
        catch(Throwable any)
        {
            if(any instanceof OutOfMemoryError)
                System.out.println((new StringBuilder()).append("OutOfMemoryError triggered! ").append(any.getMessage()).append(" [").append(any).append("]").toString());
            else
                System.out.println((new StringBuilder()).append("Unexpected Exception! ").append(any.getMessage()).append(" [").append(any).append("]").toString());
        }
        System.out.println((new StringBuilder()).append("Map Size: ").append((float)leakingMap.toString().getBytes().length / 1048576F).append("MB").toString());
        printMemoryUsage();
        System.out.println("Simulator done!");
    }

    public static void printMemoryUsage()
    {
        System.out.println();
        MemoryPoolMXBean mx;
        for(Iterator i$ = ManagementFactory.getMemoryPoolMXBeans().iterator(); i$.hasNext(); System.out.println((new StringBuilder()).append(":> ").append(mx.getType()).append("---").append(mx.getName()).append(": ").append((float)mx.getUsage().getMax() / 1048576F).append("MB").toString()))
            mx = (MemoryPoolMXBean)i$.next();

        System.out.println();
    }

    private static final int NO_ITERATIONS = 28672;
    private static final String LEAKING_DATA_PREFIX = "datadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadatadadatadat";
    static Map leakingMap = new HashMap();
}
