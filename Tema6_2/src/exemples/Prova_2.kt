package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet

fun main(args: Array<String>) {
	val bd = Db4oEmbedded.openFile("Empleats.db4o")
	
	var patro = Empleat(null,null,null,null,null,null,null,null,null,null)
	var llista: ObjectSet<Empleat> = bd.queryByExample(patro)
	println(llista.size)
	while (llista.hasNext()) {
		val e: Empleat = llista.next()
		println("Nif: " + e.nif + ". Nom: " + e.nom + ". Població: " + e.adreca?.poblacio)
		println("Primer correu: " + e.correus_e!![0] + ". Primer telèfon: " + e.telefons!![0].numero)
	}
	bd.close();
}
