package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val t = sessio.beginTransaction()
	val com = sessio.load("classes.Comarca", "Columbretes")

	sessio.delete(com)

	t.commit()
	sessio.close()
}