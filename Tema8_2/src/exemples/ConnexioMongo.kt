package exemples

import com.mongodb.MongoClient
import com.mongodb.MongoException

object ConnexioMongo {
	@JvmStatic
fun main(args: Array<String>) {
	//val uri = MongoClientURI("mongodb+srv://root:root@cluster0.7uf04.mongodb.net/root?retryWrites=true&w=majority")
    var mongoClient: MongoClient? = null
    try {
        mongoClient = MongoClient("127.0.0.1", 27017)
        println("Kotlin is now connected to MongoDB!")
    } catch (e: MongoException) {
        e.printStackTrace()
    } finally {
        mongoClient!!.close()
	}
}
}