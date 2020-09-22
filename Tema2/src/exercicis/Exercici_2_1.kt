package exercicis

import java.io.File

fun main(args: Array<String>){
	val f = File("Penyagolosa.bmp")
	
	val fi = FitxerImatge_Solucionat(f)
	fi.transformaNegatiu()
	fi.transformaObscur()
	//voluntari
	fi.transformaBlancNegre()
}