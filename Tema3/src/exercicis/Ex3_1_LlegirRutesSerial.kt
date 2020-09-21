package exercicis

import java.io.DataInputStream
import java.io.FileInputStream

fun main(args:Array<String>){
	val f = DataInputStream(FileInputStream("Rutes.dat"))
	
	while (f.available()>0){
		println("Ruta: " + f.readUTF())
		println("Desnivell: " + f.readInt())
		println("Desnivell acumulat: " + f.readInt())
		val n = f.readInt()
		println("Té " + n + " punts")
		for (i in 0 until n)
			println ("Punt " + (i+1) +": "+ f.readUTF() + " (" + f.readDouble() + "," + f.readDouble() + ")")
		println()
	}
	f.close()
	
}