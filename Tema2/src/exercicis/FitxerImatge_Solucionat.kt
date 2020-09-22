package exercicis

import java.io.File
import java.io.FileInputStream
import java.io.FileOutputStream

class FitxerImatge_Solucionat(fEnt: File) {
	var f: File = File("")

	init {
		// Constructor
		// Control d'existència del fitxer i control de l'extensió .bmp (traure missatges d'error)
        // En cas que tot siga correcte, inicialitzar f amb fEnt
		if (fEnt.exists())
			if (fEnt.getName().endsWith(".bmp"))
				f = fEnt
			else
				print(fEnt.getName() +" no és un fitxer bmp")
		else
			print (fEnt.getName() + " no existeix")
 
	}

	fun transformaNegatiu() {
		// Transformar a negatiu i guardar en _n.bmp
		val f1 = FileInputStream(f)
		val f2 = FileOutputStream(File(f.getName().replace(".bmp","_n.bmp")))
		
		for (i in 1..54)
			f2.write(f1.read())
		
		var b = f1.read()
		while(b != -1) {
			f2.write(255-b)
			b = f1.read()
		}
		f2.close()
		f1.close()	

	}

	fun transformaObscur() {
		// Transformar a una imatge més fosca i guardar en _o.bmp
		val f1 = FileInputStream(f)
		val f2 = FileOutputStream(File(f.getName().replace(".bmp","_o.bmp")))
		
		for (i in 1..54)
			f2.write(f1.read())
		
		var b = f1.read()
		while(b != -1) {
			f2.write(b/2)
			b = f1.read()
		}
		f2.close()
		f1.close()	

	}
	
	// Partvoluntària
	fun transformaBlancNegre() {
		// Transformar a una imatge en blanc i negre i guardar en _bn.bmp
		val f1 = FileInputStream(f)
		val f2 = FileOutputStream(File(f.getName().replace(".bmp","_bn.bmp")))
		
		for (i in 1..54)
			f2.write(f1.read())
		
		var r = f1.read()
		var g = 0
		var b = 0
		while(r != -1) {
			g = f1.read()
			b = f1.read()
			f2.write((r+g+b)/3)
			f2.write((r+g+b)/3)
			f2.write((r+g+b)/3)
			r = f1.read()
		}
		f2.close()
		f1.close()	

	}
 
}