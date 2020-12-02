package exemples

import classes.SessionFactoryUtil
import classes.Poblacio
import classes.Comarca

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val q = sessio.createQuery("select avg(altura) from Poblacio")

	val mitjana = q.uniqueResult ()

	println("Altura mitjana: " + mitjana)

	sessio.close()
}