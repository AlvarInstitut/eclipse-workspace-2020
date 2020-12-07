package exercicis

import com.db4o.Db4oEmbedded
import utilBd.GestionarRutesBD
import utilBd.Ruta

fun main(){
	val bd = Db4oEmbedded.openFile("Rutes.db4o")
	for (r in GestionarRutesBD().llistat())
		bd.store(r)
	bd.close()
}