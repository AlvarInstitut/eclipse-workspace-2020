package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet

import classesEmpleat.Empleat

fun main() {
	var bd = Db4oEmbedded.openFile("Empleats.db4o")

	val e =  Empleat("11111111a")
	val llista = bd.queryByExample<Empleat>(e)
	if (llista.hasNext()) {
		var e1 = llista.next()
		if (e1.sou != null) {
			e1.sou = e1.sou.toString().toDouble() + 200.0
		}

		bd.close() // Tanquem i tornem a obrir la BD, per veure que hem
		// perdut la correspondència de e amb l'objecte de la BD
		
		bd = Db4oEmbedded.openFile("Empleats.db4o")

		bd.store(e1)
	}
	bd.close()
}