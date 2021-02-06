package exemples;
import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class Prova1 {

    public static void main(String[] args) {
        MongoClient con = new MongoClient("localhost" , 27017);
        MongoDatabase bd = con.getDatabase("test");
        
        Document doc = new Document();
        doc.put("msg4", "Missatge inserit des de Java");
        bd.getCollection("exemple").insertOne(doc);
        
        con.close();
    }
}