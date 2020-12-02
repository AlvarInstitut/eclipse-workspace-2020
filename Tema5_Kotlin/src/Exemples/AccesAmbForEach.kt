package Exemples

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import classes.Comarca

fun main(args: Array<String>) {
	val sf = Configuration().configure().buildSessionFactory()
	val sessio = sf.openSession()
	val q = sessio.createQuery("from Comarca order by nomC")
	
	for (com in q.list()) {
		com as Comarca
		println(com.nomC + " - " + com.provincia)
	}

}