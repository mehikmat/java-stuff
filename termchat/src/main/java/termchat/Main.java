package termchat;

/**
 * @author Hikmat Dhamee
 * @email me.hemant.available@gmail.com
 */
public class Main {
    public static void main(String[] args) {

        if (args.length == 0){
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(" USAGE: $termchat [server|client] ");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            return;
        }

        if ("server".equalsIgnoreCase(args[0])){
            Thread server = new Thread(DiscoveryThreadServer.getInstance());
            server.start();
        }else if ("client".equalsIgnoreCase(args[0])){
            Thread client = new Thread(DiscoveryThreadClient.getInstance());
            client.start();
        }else{
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>                    >>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>> Invalid Command!!! >>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>                    >>>>>>>>>>");
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
    }
}

