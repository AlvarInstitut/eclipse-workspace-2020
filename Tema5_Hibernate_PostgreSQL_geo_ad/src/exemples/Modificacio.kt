package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val t = sessio.beginTransaction()
	val com = sessio.load("classes.Comarca", "Camp de Morvedre") as Comarca
	com.provincia = "Castelló"
	
	sessio.update(com)

	t.rollback()
	
	sessio.close()
}