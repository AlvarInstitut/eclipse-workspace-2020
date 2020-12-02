package classes

import org.hibernate.cfg.Configuration
import classes.Usuari

fun main(args: Array<String>) {
	val sf = Configuration().configure().buildSessionFactory()
	val sessio = sf.openSession()
	val u1 = sessio.load("classes.Usuari", 1) as Usuari
	print("Usuari " + u1.nom + ": ")
	//print(com.provincia)
	//println(" (" + com.poblacios?.size + " pobles)")
	sessio.close() 
}