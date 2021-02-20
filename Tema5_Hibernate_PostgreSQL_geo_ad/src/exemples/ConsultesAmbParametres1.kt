package exemples

import classes.SessionFactoryUtil
import classes.Poblacio

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val q = sessio.createQuery("from Poblacio where altura>=? and comarca.nomC=?")
	q.setInteger(0, 500)
	q.setString(1, "Alcalatén")

	q.list().forEach {
		it as Poblacio
		println(it.nom + " - " + it.altura)
	}

	sessio.close()
}