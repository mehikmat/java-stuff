package demo.cassandra.net;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Connection {
   private static Cluster cluster;
   private static Properties properties;
   private static final String configFileName = "cluster_config.properties";

    public Connection() {
        properties = new Properties();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(configFileName);
        if (inputStream != null){
            try {
                properties.load(inputStream);
                System.out.println("ClusterConfig: " + properties.toString());
            } catch (IOException e) {
                throw  new RuntimeException(e.getLocalizedMessage());
            }
        }
    }

    public Session getConnection() {
        // Connection to the cluster and keyspace "demo"
        cluster = Cluster.builder().addContactPoint(properties.getProperty("cluster.url")).build();
        return cluster.connect(properties.getProperty("keyspace.name"));
    }
}