package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val q = sessio.createQuery("from Comarca")

	val it = q.iterate ()
	
	while (it.hasNext()) {
		val com = it.next() as Comarca
		println(com.nomC + " -- " + com.provincia)
	}
	sessio.close()
}