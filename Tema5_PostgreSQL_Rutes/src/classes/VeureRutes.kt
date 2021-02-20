package classes

fun main(){
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val q = sessio.createQuery ("from Ruta order by nomR")

	for (r in q.list()){
		r as Ruta
		println(r.nomR + " - " + r.punts?.size + " punts")
	}

	sessio.close()
}