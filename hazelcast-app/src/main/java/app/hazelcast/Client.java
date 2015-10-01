package app.hazelcast;

import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;

/**
 * Created by hikmat on 6/7/14.
 */
public class Client {


        public static void main(String[] args) {
            ClientConfig clientConfig = new ClientConfig();
            clientConfig.addAddress("127.0.0.1:5701");
            HazelcastInstance client = HazelcastClient.newHazelcastClient(clientConfig);
            IMap map = client.getMap("customers");
            IMap que = client.getMap("customers");

            System.out.println("Map Content:>"+map.keySet().toString());
            System.out.println("Que Content:>"+que.keySet().toString());
        }

}
