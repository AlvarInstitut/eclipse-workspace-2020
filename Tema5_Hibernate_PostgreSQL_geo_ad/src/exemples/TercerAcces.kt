package exemples

import classes.Comarca
import classes.SessionFactoryUtil

fun main(args: Array<String>) {
	val sF = SessionFactoryUtil.sessionFactory
	val sessio = sF.openSession()
	val com = sessio.load("classes.Comarca", "Alcalatén") as Comarca
	print("La comarca " + com.nomC)
	print(" (província de " + com.provincia + ") ")
	println("té " + com.poblacios?.size + " pobles")
	println()
	println("Llista de pobles")
	println("-----------------")

	val it = com.poblacios?.iterator()!!

	while (it.hasNext()) {
		val p = it.next()
		System.out.println(p.nom + " (" + p.poblacio + " habitants)")
	}

	sessio.close()
}
