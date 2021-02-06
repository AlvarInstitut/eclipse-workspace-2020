package exemples

import redis.clients.jedis.Jedis

fun main() {
	val con = Jedis("localhost")
	con.connect()

	val valor = "Aquesta clau és una clau creada des de Java"
	con.set("clau_Java2", valor)

	println(con.get("clau_Java2"))
	con.close()

}