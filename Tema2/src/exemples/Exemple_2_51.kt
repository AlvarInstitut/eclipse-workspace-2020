package exemples

import java.io.BufferedReader
import java.io.FileReader
import java.io.PrintWriter
import java.io.FileWriter

fun main(args: Array<String>) {
	val f_ent = BufferedReader(FileReader ("f7_ent.txt"))
	val f_eix = PrintWriter(FileWriter ("f7_eix.txt"))
	var cad = f_ent.readLine();
	var i = 0
	while (cad != null) {
		i++
		f_eix.println("" + i + ".- " + cad)
		cad = f_ent.readLine()
	}
	f_eix.close()
	f_ent.close()
}
