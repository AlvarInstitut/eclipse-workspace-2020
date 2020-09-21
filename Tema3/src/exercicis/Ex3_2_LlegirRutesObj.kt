package exercicis

import java.io.ObjectInputStream
import java.io.FileInputStream
import java.io.IOException

fun main(args: Array<String>) {
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))
	println("Anem a mostrar les rutes")
	println("--------------------------------------")
	try {
		while (true) {
			val r = f.readObject() as Ruta
			r.mostrarRuta()
			println("--------------------------------------")

		}
	} catch (ex: IOException) {
		f.close()
	}
}