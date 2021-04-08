package classesBiblio


fun main() {
	val sF = SessionFactoryUtil.sessionFactory
	val sessio = sF.openSession()

	val q = sessio.createQuery("from Editorial order by nom")
	val editorials = q.list()

	for (e in editorials as List<Editorial>) {
		println("Editorial: " + e.nom)
		for (ll in e.llibres!!){
			var text = "\t" + ll.isbn + " - " + ll.titol + " ("
			for (a in ll.autors!!)
				text += a.nom + " - "
			text = text.substring(0,text.length-3) + ")"
			println(text)
		}
		println()
	}
	sessio.close()
}