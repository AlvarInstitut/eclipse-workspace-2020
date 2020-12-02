package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FitxerImatge(fEnt: File) {
	var f: File = File("")

	init {
		// Constructor
		// Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
		// En cas que tot siga correcte, inicialitzar f amb fEnt
		if (fEnt.exists() && fEnt.getName().endsWith(".bmp"))
			f = fEnt
		else
			println("No és un fitxer d'imatge")

	}

	fun transformaNegatiu() {
		// Transformar a negatiiu i guardar en _n.bmp
		val f_in = FileInputStream(f)
		val f_out = FileOutputStream(f.getName().replace(".bmp", "._n.bmp"))

		for (i in 0..53)
			f_out.write(f_in.read())

		var b = f_in.read()
		while (b != -1) {
			f_out.write(255 - b)
			b = f_in.read()
		}
		f_out.close()
		f_in.close()
	}

	fun transformaObscur() {
		// Transformar a una imatge més fosca i guardar en _o.bmp
		val f_in = FileInputStream(f)
		val f_out = FileOutputStream(f.getName().replace(".bmp", "._o.bmp"))

		for (i in 0..53)
			f_out.write(f_in.read())

		var b = f_in.read()
		while (b != -1) {
			f_out.write(b / 2)
			b = f_in.read()
		}
		f_out.close()
		f_in.close()

	}

	// Partvoluntària
	fun transformaBlancNegre() {
		// Transformar a una imatge en blanc i negre i guardar en _bn.bmp
		val f_in = FileInputStream(f)
		val f_out = FileOutputStream(f.getName().replace(".bmp", "._bn.bmp"))

		for (i in 0..53)
			f_out.write(f_in.read())

		var r = f_in.read()
		while (r != -1) {
			val g = f_in.read()
			val b = f_in.read()
			val m = (r +g + b) / 3
			f_out.write(m)
			f_out.write(m)
			f_out.write(m)
			r = f_in.read()
		}
		f_out.close()
		f_in.close()
	}

}