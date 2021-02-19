package biblioteca

import com.db4o.Db4oEmbedded


fun main() {
	val bd = Db4oEmbedded.openFile("Biblioteca.db4o")

	val patro = Soci()

	val llista = bd.queryByExample<Soci>(patro)
	var i = 1
	for (s in llista) {
		print("" + i + ".- " + s.nom)
		if (s.llPrestecs?.size == 0)
			println(" (no té préstecs)")
		else {
			println()
			for (p in s.llPrestecs!!)
				println("\t--> " + p.isbn + " " + p.titol + " (" + p.autor?.nom + ")")
		}
		println()
	}

	bd.close()

}