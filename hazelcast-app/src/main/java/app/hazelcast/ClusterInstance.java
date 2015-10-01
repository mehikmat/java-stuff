package app.hazelcast;

import com.hazelcast.core.*;
import com.hazelcast.config.*;

import java.util.Map;
import java.util.Queue;
/**
 * Created by hikmat on 6/7/14.
 */
public class ClusterInstance {

        public static void main(String[] args) {
            Config cfg = new Config();
            HazelcastInstance instance = Hazelcast.newHazelcastInstance(cfg);
            Map<Integer, String> mapCustomers = instance.getMap("customers");
            mapCustomers.put(4, "Joe");
            mapCustomers.put(5, "Ali");
            mapCustomers.put(6, "Avi");

            System.out.println("Customer with key 1: "+ mapCustomers.get(1));
            System.out.println("Map Size:" + mapCustomers.size());

            Queue<String> queueCustomers = instance.getQueue("customers");
            queueCustomers.offer("Tom");
            queueCustomers.offer("Mary");
            queueCustomers.offer("Jane");

            System.out.println("First customer: " + queueCustomers.poll());
            System.out.println("Second customer: "+ queueCustomers.peek());
            System.out.println("Queue size: " + queueCustomers.size());


        }
}
