package demo;

import com.sun.management.UnixOperatingSystemMXBean;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;

public class TooManyOpenFiles{

    public static void main(String[] args) {
        /**
         * The management interface for the operating system on which
         * the Java virtual machine is running.
         *
         * A Java virtual machine has a single instance of the implementation
         * class of this interface.
         *
         * This interface defines several convenient methods for accessing
         * system properties about the operating system on which the Java
         * virtual machine is running.
         */
        OperatingSystemMXBean os = ManagementFactory.getOperatingSystemMXBean();

        System.out.println("OS Arch: " + os.getArch());
        System.out.println("OS Name: " + os.getName());
        System.out.println("OS Ver: " + os.getVersion());
        System.out.println();

        // check if the underlying OS is Unix/Linux
        if(os instanceof UnixOperatingSystemMXBean){

            // throws Too many open files exception if j reaches to 4080
            // since 17 files are already open by this process(by JVM for this) and 4080 files we are opening
            // so total open files count reaches to 4080+17=4097 > 4096( limit on open files per process)
            for (int j = 1; j <= 4080; j++) {

                if (j <= 4079)
                    System.out.println("Number of open FD Count: " + ((UnixOperatingSystemMXBean) os).getOpenFileDescriptorCount());

                File file1 = new File("/tmp/test/abc.txt-" + j);
                FileOutputStream fileOutputStream1;

                try {
                    fileOutputStream1 = new FileOutputStream(file1);
                    DataOutputStream dataOutputStream1 = new DataOutputStream(fileOutputStream1);
                    dataOutputStream1.write("".getBytes());
                } catch (IOException e) {
                    System.out.println();
                    System.out.println(">>>>>>> " + e.getClass().getSimpleName() + ":: " + e.getLocalizedMessage());
                    System.exit(1);
                }
            }

        }
    }
}