package exercicis

import com.db4o.Db4oEmbedded
import utilBd.Ruta

fun main(){
	val bd = Db4oEmbedded.openFile("Rutes.db4o")
	val q = bd.queryByExample<Ruta>(Ruta(null,null,null))
	
	for (r in q)
		println(r.nom + ": " + r.llistaDePunts.size + " punts")
	bd.close()
}