package libraries;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by hdhamee on 6/16/16.
 */
public class Java8Base64 {
    //the support of Java8Base64 encoding has made its way into Java standard library with Java 8 release.
    public static void main(String[] args) {
        final String text = "Base64 finally in Java 8!";

        final String encoded = Base64
                .getEncoder()
                .encodeToString( text.getBytes( StandardCharsets.UTF_8 ) );
        System.out.println( "encoded: " + encoded );

        final String decoded = new String(
                Base64.getDecoder().decode( encoded ),
                StandardCharsets.UTF_8 );
        System.out.println( "decoded: " + decoded );
    }
}
