package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet
import com.db4o.config.EmbeddedConfiguration

import classesEmpleat.Adreca
import classesEmpleat.Empleat

fun main() {
	val conf = Db4oEmbedded.newConfiguration()
	conf.common().objectClass("classesEmpleat.Empleat").cascadeOnUpdate(true)

	val bd = Db4oEmbedded.openFile(conf, "Empleats.db4o")

	val e = Empleat("11111111a")
	val llista = bd.queryByExample<Empleat>(e)
	if (llista.hasNext()) {
		var e1 = llista.next()
		if (e1.sou != null) {
			e1.sou = e1.sou.toString().toDouble() + 200.0
		}
		val adr = e1.adreca
		adr?.carrer = "Pl. Rei en Jaume, 15"
		adr?.codipostal = "12002"
		e1.adreca = adr
		bd.store(e1)
	}
	bd.close()

}
