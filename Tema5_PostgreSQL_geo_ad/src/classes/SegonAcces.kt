package dades

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import classes.Comarca;
import classes.SessionFactoryUtil
//import dades.SessionFactoryUtil;

fun main(args: Array<String>) {

	val sf = SessionFactoryUtil.getSessionFactory()
	val sessio = sf.openSession()
	val com = sessio.load("dades.Comarca", "Alt Maestrat") as Comarca
	print("Comarca " + com.nomC + ": ")
	print(com.provincia)
	println(" (" + com.poblacios?.size + " pobles)")
//	for(p in com.poblacios)
//		println("\t" + p.getNom())
	sessio.close()

}