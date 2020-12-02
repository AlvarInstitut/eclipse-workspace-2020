package exemples

import classes.SessionFactoryUtil
import classes.Poblacio

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val q = sessio.createQuery("from Poblacio where altura>=:alt and comarca.nomC=:com")
	q.setInteger("alt", 500)
	q.setString("com", "Alcalatén")

	q.list().forEach {
		it as Poblacio
		println(it.nom + " -- " + it.altura)
	}

	sessio.close()
}