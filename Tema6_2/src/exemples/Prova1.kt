package exemples

import com.db4o.Db4oEmbedded

fun main(args: Array<String>) {
	val bd = Db4oEmbedded.openFile("Empleats.db4o");

	var e = Empleat("11111111a","Albert",10,45,1000.0,null,null,null,null,null)
/*	var e = Empleat("11111111a")
	e.nom="Albert"
	e.departament=10
	e.edat= 45
	e.sou= 1000.0
*/
	// les dades més complicades les introduïm de forma especial
	e.adreca = Adreca("C/ Major, 7", "12001", "Castelló")
	e.correus_e = arrayOf("alu11111111a@ieselcaminas.org")
	e.telefons = arrayOf(Telefon(true, "666777888"), Telefon(false, "964112233"))

	bd.store(e);

	bd.close();
}
