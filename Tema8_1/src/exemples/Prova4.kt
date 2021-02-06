package exemples

import redis.clients.jedis.Jedis

fun main() {
	val con = Jedis("89.36.214.106")
	con.connect()
	con.auth("ieselcaminas.ad")
	//con.set("clau_1","Primera prova de redis en el servidor")

	val c = con.keys("*")  //c és un MutableSet
	for (s in c)
		println(s + " --> " + con.get(s))

	con.close()
}