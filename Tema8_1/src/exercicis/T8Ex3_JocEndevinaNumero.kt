package exercicis

import java.util.Scanner
import redis.clients.jedis.Jedis

fun main(){
	val secret:Int = (Math.random()*100 +1).toInt()
	val scan = Scanner(System.`in`)
	val tIni =  System.currentTimeMillis()
	println("Introdueix un número:")
	var jug = scan.nextInt()
	while(jug != secret){
		if (secret > jug)
			println("És major")
		else
			println("Ès menor")
		jug = scan.nextInt()
	}
	println ("Molt bé !! És el " + secret)
	val tFin =  System.currentTimeMillis()
	val tUt: Double = (tFin-tIni)/1000.0
	println("Temps utilitzat: " + tUt)
	println("Introdueix el teu nom:")
	var nom = scan.next()
	val con = Jedis("89.36.214.106")
	con.auth("ieselcaminas.ad")
	if (con.zscore("joc_marques",nom)!=null){
		var i = 1
		var nom1=nom +"_" + i
		while(con.zscore("joc_marques",nom1)!=null){
			i=i+1
			nom1=nom +"_" + i
		}
		nom=nom1
	}
		
	con.zadd("joc_marques",tUt,nom)
	
	println("Llista de puntuacions")
	println("---------------------")
	for (t in con.zrangeWithScores("joc_marques",0,9))
		println(t.getElement() + " ---> " + t.getScore())
	con.close()
}