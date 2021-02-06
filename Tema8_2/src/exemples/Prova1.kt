package exemples


import org.bson.Document
import com.mongodb.MongoException
import com.mongodb.MongoClient

//import com.mongodb.MongoClientURI


fun main(args: Array<String>) {
        val con = MongoClient("localhost" , 27017);
        val bd = con.getDatabase("test");
        
        val doc = Document();
        doc.put("msg4", "Missatge inserit des de Kotlin");
        bd.getCollection("exemple").insertOne(doc);
        
        con.close();

}


