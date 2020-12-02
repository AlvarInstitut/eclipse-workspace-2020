package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet

import classesEmpleat.Empleat

fun main() {
	val conf = Db4oEmbedded.newConfiguration()
	conf.common().objectClass("classesEmpleat.Empleat").cascadeOnDelete(true)

	val bd = Db4oEmbedded.openFile(conf,"Empleats.db4o")
	val e = Empleat("33333333c")

	// Si posàrem ací db.delete(e) no tindría efecte, perquè e no es
	// correspon amb cap instància de la BD

	val llista = bd.queryByExample<Empleat>(e)
	if (llista.hasNext()) {
		val e1 = llista.next()
		bd.delete(e1)
	}
	bd.close()
}