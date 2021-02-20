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
		for (a in p.alumnes!!){
			print("\t" + a.nom)
			var tKm = 0
			a.practiqueses?.forEach(){ tKm += (it as Practiques).km!!}
			print("\t" + tKm + " km. de practiques. ")
			println(" " + a.examens?.size + " examens.")
		}
		println()
	}
}