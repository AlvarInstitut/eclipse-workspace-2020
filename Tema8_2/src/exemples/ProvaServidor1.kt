package exemples

import com.mongodb.Mongo

fun main(args: Array<String>) {
        val con = Mongo("89.36.214.106" , 27017)
        val bd = con.getDB("test")
        bd.authenticate("ad","ieselcaminas".toCharArray())
       
        val c = bd.getCollection("libro").find()
        for (d in c)
			println(d.get("titulo"))
       
        con.close()
}