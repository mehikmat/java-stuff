package xample.avrojava;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificDatumWriter;
import xample.generated.User;

import java.io.File;
import java.io.IOException;

/**
 * Created by hdhamee on 5/16/14.
 */
public class AvroUserBook {

    public static void main(String[] args) throws IOException {
        System.exit(new AvroUserBook().run());
    }

    public int run() throws IOException {

        //Method1
        User user1 = new User();
        user1.setName("Hikmat");
        user1.setId("25");

        // method 2 vai constructor
        User user2 = new User("26","Hari");

       // method 3 Construct via builder
        User user3 = User.newBuilder()
                .setName("Charlie")
                .setId("27")
                .build();

        //Serializing
        File file = new File("res/users.avro");
        DatumWriter<User> userDatumWriter = new SpecificDatumWriter<User>(User.class);
        DataFileWriter<User> dataFileWriter = new DataFileWriter<User>(userDatumWriter);
        dataFileWriter.create(user1.getSchema(),file);
        dataFileWriter.append(user1);
        dataFileWriter.append(user2);
        dataFileWriter.append(user3);
        dataFileWriter.close();

        // Deserialize Users from disk
        DatumReader<User> userDatumReader = new SpecificDatumReader<User>(User.class);
        DataFileReader<User> dataFileReader = new DataFileReader<User>(file, userDatumReader);
        User user = null;
        while (dataFileReader.hasNext()) {
        // Reuse user object by passing it to next(). This saves us from
        // allocating and garbage collecting many objects for files with
        // many items.
        user = dataFileReader.next(user);
        System.out.println(user);
        }
        return 0;
    }
}
