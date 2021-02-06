package exemples

import redis.clients.jedis.Jedis

fun main(){
	    val con = Jedis("localhost")
        con.connect()
        
        val s = con.smembers("colors")  // s és un MutableSet
        for (e in s)
            println(e)
        
        con.close()
}