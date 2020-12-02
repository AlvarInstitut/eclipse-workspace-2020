package fitxers

import java.io.File
import java.io.DataInputStream
import java.io.FileInputStream
import java.io.PrintWriter

fun main(){
	val linies = File("entrada1.txt").readLines()
	val f2 = DataInputStream(FileInputStream("entrada2.dat"))
	val f3 = PrintWriter("eixida.txt")
	
	var acum = 0
	
	for (l in linies){
		f3.println(l)
		acum += l.toInt()
	}
	
	while(f2.available()>0){
		val n = f2.readInt()
		f3.println(n)
		acum += n
	}
	
	f3.println("Total: " + acum)
	f3.close()
	f2.close()
}