package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val d = sessio.createQuery ("from Comarca where nomC='Alcalatén'").uniqueResult() as Comarca

	println(d.nomC + " - " + d.provincia)

	sessio.close()
}