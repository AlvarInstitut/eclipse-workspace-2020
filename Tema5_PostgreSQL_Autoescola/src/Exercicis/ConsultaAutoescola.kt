package Exercicis

import classes.SessionFactoryUtil
import classes.Professor
import classes.Alumne
import classes.Practiques

fun main() {
	val s = SessionFactoryUtil.sessionFactory.openSession()
	val profs = s.createQuery("from Professor order by nom").list() as ArrayList<Professor>

	for (p in profs) {
		println(p.nom)
		val q1 = s.createQuery("from Alumne where professor.dni='" + p.dni +"' order by nom")
		val llista = q1.list()
		llista.forEach {
			it as Alumne
			print("\t" + it.nom)
			var tKm = 0
			it.practiqueses?.forEach(){ tKm += (it as Practiques).km!!}
			print("\t" + tKm + " km. de practiques. ")
			println(" " + it.examens?.size + " examens.")
		}
		println()
	}
}