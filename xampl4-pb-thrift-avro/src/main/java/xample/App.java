package xample;

import xample.nettyprotobuff.ClockClient;
import xample.nettyprotobuff.ClockServer;

import java.util.HashMap;
import java.util.Map;

/**
 * Example class for google protobuff, apache avro, apche thrift
 *
 */
public class App {
    public static void main( String[] args ) throws InterruptedException {

        Map<String, Character> app = new HashMap<String, Character>() {
            {
                put("avro", 'a');
                put("proto", 'p');
                put("thrift", 't');
            }
        };
        switch(app.get("proto")){
            case 'p':
                  Thread t = new Thread(new Runnable() {
                   @Override
                   public void run() {
                       try {
                           ClockClient.main(new String[]{});
                       } catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
               });
               t.start();

                Thread thread= new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            ClockServer.main(new String[]{});
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

                break;
           default:
               System.out.println("No halder for that!!!");

        }
    }
}
