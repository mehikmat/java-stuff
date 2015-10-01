import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class MongoDBJDBC{
    public static void main( String args[] ){
        try{
            // To connect to mongodb server
            MongoClient mongoClient = new MongoClient( "localhost" , 27017 );

            // Now connect to your databases
            DB db = mongoClient.getDB( "student_db" );

            System.out.println("Connect to database successfully");

            //boolean auth = db.authenticate(myUserName, myPassword);
            //System.out.println("Authentication: "+auth);

            // To create new collection (sql table)
            DBCollection coll = db.createCollection("demo_coll",null);
            System.out.println("Collection created successfully");

            // To insert new document in collection (sql row)
            BasicDBObject doc = new BasicDBObject("title", "MongoDB").
                    append("description", "database").
                    append("likes", 100).
                    append("url", "http://www.tutorialspoint.com/mongodb/").
                    append("by", "tutorials point");
            coll.insert(doc);
            System.out.println("Document inserted successfully");

        }catch(Exception e){
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
        }
    }
}