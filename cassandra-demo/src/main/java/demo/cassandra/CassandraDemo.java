package demo.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

/**
 * Created by hdhamee on 10/16/15.
 */
public class CassandraDemo {
    public static void main(String[] args) {
        Cluster cluster;
        Session session;

        // Connect to the cluster and keyspace "demo"
        cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        session = cluster.connect("mykeyspace");

        // Insert one record into the users table
        session.execute("INSERT INTO users (lname,fname,user_id) VALUES ('Dhamee','Hikmat',38)");

        // Use select to get the user we just entered
        ResultSet results = session.execute("SELECT * FROM users WHERE user_id=35");
        for (Row row : results) {
            System.out.format("%s %s\n", row.getString("fname"), row.getString("lname"));
        }

        // Update the same user with a new age
        session.execute("update users set lname = 'Dhamee' where user_id = 35");
        // Select and show the change
        results = session.execute("select * from users where user_id=35");
        for (Row row : results) {
            System.out.format("%s %s\n", row.getString("fname"), row.getString("fname"));

        }

        // Delete the user from the users table
        session.execute("DELETE FROM users WHERE user_id = 35");
        // Show that the user is gone
        results = session.execute("SELECT * FROM users");
        for (Row row : results) {
            System.out.format("%s %s %d\n", row.getString("lname"), row.getString("fname"), row.getInt("user_id"));
        }

        // Clean up the connection by closing it
        cluster.close();
    }
}
