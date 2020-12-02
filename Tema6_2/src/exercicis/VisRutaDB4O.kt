package exercicis

import com.db4o.Db4oEmbedded
import util.bd.Ruta
import util.bd.PuntGeo

fun main() {
	val bd = Db4oEmbedded.openFile("Rutes.db4o")
	val llista = bd.queryByExample<Ruta>(Ruta(null,null,null))
	for (r in llista)
		println(r.nom + ": " + r.llistaDePunts.size + " punts")
	bd.close()

}