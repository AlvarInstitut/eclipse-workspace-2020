package dades

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import dades.Comarca

fun main(args: Array<String>) {
	val sf = Configuration().configure().buildSessionFactory()
	val sessio = sf.openSession();
	val com = sessio.load("dades.Comarca","Alt Maestrat") as Comarca
	print("Comarca " + com.nomC + ": ")
	print(com.getProvincia());
	println(" (" + com.getPoblacios().size + " pobles)");
	sessio.close();
}
