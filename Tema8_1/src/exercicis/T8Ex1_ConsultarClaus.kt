package exercicis

import redis.clients.jedis.Jedis
import java.util.Scanner

fun main() {
	val con = Jedis("89.36.214.106")
	con.auth("ieselcaminas.ad")

	val claus = ArrayList(con.keys("*"))
	var i = 1
	for (c in claus) {
		println("" + i + ".- " + c)
		i++
	}
	println("Introdueix un número (0 per a eixir)")
	val scan = Scanner(System.`in`)
	var num = scan.nextInt()
	while (num != 0) {
		if (num > 0 && num < claus.size) {
			val clau = claus.get(num - 1)
			when (con.type(clau).toString()) {
				"string" -> println(clau + ": " + con.get(clau))
				"hash" -> {
					println(clau)
					for (camp in con.hkeys(clau))
						println("\t" + camp + " --> " + con.hget(clau, camp))
				}
				"list" -> {
					println(clau)
					for (e in con.lrange(clau, 0, -1))
						println("\t" + e)
				}
				"set" -> {
					println(clau)
					for (e in con.smembers(clau))
						println("\t" + e)
				}
				"zset" -> {
					println(clau)
					for (c in con.zrangeWithScores(clau, 0, -1))
						println("\t" + c.getElement() + " --> " + c.getScore())
				}
			}
		}
		println("Introdueix un número (0 per a eixir)")
		num = scan.nextInt()

	}
	con.close()
}