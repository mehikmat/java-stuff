package termchat;

import java.net.InetAddress;

/**
 * @author Hikmat Dhamee
 * @email 
 */
public class AppData {
    private static String inetAddress_ = null;

    public static String getInetAddress_() {
        return inetAddress_;
    }
    public static void setServerIp(String inetAddress){
        inetAddress_ = inetAddress;
    }
}
