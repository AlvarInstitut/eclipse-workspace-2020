package exemples

import classes.SessionFactoryUtil
import classes.Poblacio
import classes.Comarca

fun main(args: Array<String>) {
	val sessio = SessionFactoryUtil.sessionFactory.openSession()

	val q = sessio.createQuery("from Poblacio p, Comarca c where p.comarca.nomC=c.nomC order by p.nom")

	val it = q.iterate()
	while (it.hasNext()) {
		val tot = it.next() as Array<Object>
		val p = tot[0] as Poblacio
		val c = tot[1] as Comarca
		println(p.nom + " (" + c.nomC + ". " + c.provincia + ")")
	}

	sessio.close()
}