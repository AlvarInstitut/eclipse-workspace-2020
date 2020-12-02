package classes

import org.hibernate.cfg.Configuration

fun main(){
	val sessio = SessionFactoryUtil.sessionFactory.openSession()
	val q = sessio.createQuery ("from Ruta")

	q.list().forEach {
		it as Ruta
		println(it.nomR + " - " + it.punts?.size + " punts")
	}

	sessio.close()
}