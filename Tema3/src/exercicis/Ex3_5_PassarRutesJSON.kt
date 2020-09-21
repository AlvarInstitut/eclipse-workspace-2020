package exercicis

import java.io.ObjectInputStream
import java.io.FileInputStream
import java.io.IOException
import com.squareup.moshi.Moshi
import java.io.File

class Rutes(var rutes: MutableList<Ruta> = mutableListOf<Ruta>())

fun main(args: Array<String>) {
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))
	val rutes = Rutes()

	try {
		while (true) {
			rutes.rutes.add(f.readObject() as Ruta)
		}
	} catch (ex: IOException) {
		f.close()
		val moshi = Moshi.Builder().build()
		val adapter = moshi.adapter(Rutes::class.java)
		val json = adapter.toJson(rutes)

		File("Rutes.json").writeText(json)
	}
}