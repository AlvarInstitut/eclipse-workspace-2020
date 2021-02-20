package exemples

import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val t = sessio.beginTransaction()

	val files = sessio.createQuery("update Poblacio set poblacio = poblacio *1.05 where poblacio < 200").executeUpdate()

	println("S'han modificat " + files + " files.")

	t.rollback()

	sessio.close()
}