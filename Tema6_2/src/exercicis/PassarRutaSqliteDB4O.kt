package exercicis

import com.db4o.Db4oEmbedded
import util.bd.*

fun main(){
	val bd = Db4oEmbedded.openFile("Rutes.db4o")
	bd.store(GestionarRutesBD().llistat())
	bd.close()
}