package redis

import redis.clients.jedis.Jedis

fun main(){
	val con = Jedis("89.36.214.106")
	con.auth("ieselcaminas.ad")

	for (i in 1..12)
		con.sadd("Baralla_15","O"+i)
	for (i in 1..12)
		con.sadd("Baralla_15","C"+i)
	for (i in 1..12)
		con.sadd("Baralla_15","E"+i)
	for (i in 1..12)
		con.sadd("Baralla_15","B"+i)
	
	for (i in 1..4) {
		print("Judador " + i + " ---> ")
		for (j in 1..6)
			print(con.spop("Baralla_15") + " ")
		println()
	}
	println("Cartes sobrants: ")
	while(con.exists("Baralla_15"))
		print(con.spop("Baralla_15") + " ")
	println()
	con.close()
}
