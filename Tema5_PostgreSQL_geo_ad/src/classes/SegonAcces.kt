package dades

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import dades.Comarca;
//import dades.SessionFactoryUtil;

fun main(args: Array<String>) {

	val sf = SessionFactoryUtil.getSessionFactory()
	val sessio = sf.openSession()
	val com = sessio.load("dades.Comarca", "Alt Maestrat") as Comarca
	print("Comarca " + com.getNomC() + ": ")
	print(com.getProvincia())
	println(" (" + com.getPoblacios().size + " pobles)")
	for(p in com.getPoblacios())
		println("\t" + p.getNom())
	sessio.close()

}