package json

import java.io.File
import java.io.PrintWriter

fun main(args: Array<String>){
	val linies = File("PoblacioSexe.csv").readLines()
	val liniesFinal = arrayListOf<String>()
	for (l in linies){
		var camps = l.split(";")
		println(camps.get(0)+";"+ camps.get(2).substring(3) + ";" + camps.get(3).substring(camps.get(3).length-4) + ";" + camps.get(4))
		liniesFinal.add(camps.get(0)+";"+ camps.get(2).substring(3) + ";" + camps.get(3).substring(camps.get(3).length-4) + ";" + camps.get(4))
	}
	val f = PrintWriter("PoblacioSexeOrdenat.csv")
	for (l in liniesFinal)
		f.println(l)
	f.close()
}