package Exemples

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import classes.Comarca

fun main(args: Array<String>) {
	println(args.size)
	val sf = Configuration().configure().buildSessionFactory()
	val sessio = sf.openSession();
	val com = sessio.load("classes.Comarca","Alt Maestrat") as Comarca
	print("Comarca " + com.nomC + ": ")
	print(com.provincia)
	println(" (" + com.poblacios?.size + " pobles)")
	sessio.close()
	println("Per favor va vinga")
}
