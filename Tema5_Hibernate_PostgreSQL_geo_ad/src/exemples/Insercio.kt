package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val t = sessio.beginTransaction ()
	val com = Comarca()

	com.nomC = "Columbretes"
	com.provincia = "Castelló"

	sessio.save(com)

	t.commit()
	sessio.close()
}