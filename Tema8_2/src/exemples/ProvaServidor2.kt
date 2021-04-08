package exemples

import com.mongodb.Mongo
import com.mongodb.BasicDBObject

fun main() {
	val con = Mongo("89.36.214.106", 27017)

	val bd = con.getDB("test")
	bd.authenticate("ad", "ieselcaminas".toCharArray())
	val cond = BasicDBObject()
	val c1 = BasicDBObject()
	c1.put("\$gt",10)
	cond.put("precio", c1)
	val ord = BasicDBObject()
	ord.put("precio", -1)
	val c = bd.getCollection("libro").find(cond).sort(ord)
	for (d in c)
		println("" + d.get("titulo") + " --> " +d.get("precio"))

	con.close()
}