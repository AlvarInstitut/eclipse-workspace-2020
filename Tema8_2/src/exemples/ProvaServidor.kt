package exemples

import com.mongodb.Mongo
import com.mongodb.BasicDBObject

fun main(args: Array<String>) {
        val con = Mongo("89.36.214.106" , 27017)
        val bd = con.getDB("test")
        bd.authenticate("ad","ieselcaminas".toCharArray())
	
       	val doc = BasicDBObject()
       	doc.put("msg4","Missatge inserit des de Kotlin")
        val c = bd.getCollection("exemple").insert(doc)     
       
        con.close()
}