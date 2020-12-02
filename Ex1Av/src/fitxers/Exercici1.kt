package fitxers

import java.io.File
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.PrintWriter

fun main(){
	val f1 = File("dades.txt")
	val f2 = DataInputStream(FileInputStream("dades.dat"))
	val f3 = PrintWriter(File("resum.txt"))
	val linies = f1.readLines()
	for (l in linies){
		f3.println(l + ": " + f2.readFloat() + "m. - " + f2.readInt() + "kg.")
	}
	f3.close()
	f2.close()
	
}