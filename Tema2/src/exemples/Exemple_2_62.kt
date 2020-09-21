package exemples

import java.io.FileReader
import java.io.BufferedReader
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter

fun main(args: Array<String>) {
	val f_ent = BufferedReader(FileReader ("f5.txt"))
	val f_eix = PrintWriter(OutputStreamWriter (FileOutputStream ("f5_ISO.txt"), "ISO-8859-1"))

	var cad = f_ent.readLine()
	while (cad != null) {
		f_eix.println(cad)
		cad = f_ent.readLine()
	}
	f_eix.close()
	f_ent.close()
}