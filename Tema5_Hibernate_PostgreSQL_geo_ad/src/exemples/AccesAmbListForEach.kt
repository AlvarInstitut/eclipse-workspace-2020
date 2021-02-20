package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val q = sessio.createQuery ("from Comarca")

	q.list().forEach {
		it as Comarca
		println(it.nomC + " --- " + it.provincia)
	}

	sessio.close()
}