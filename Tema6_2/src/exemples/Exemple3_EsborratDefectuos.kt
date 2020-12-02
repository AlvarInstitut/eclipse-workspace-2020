package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet

import classesEmpleat.Empleat

fun main() {

	val bd = Db4oEmbedded.openFile("Empleats.db4o")
	val e = Empleat("22222222b")

	// Si posàrem ací db.delete(e) no tindría efecte, perquè e no es
	// correspon amb cap instància de la BD

	val llista = bd.queryByExample<Empleat>(e) 
	if (llista.hasNext()) {
		val e1 = llista.next()
		bd.delete(e1)
	}
	bd.close()
}