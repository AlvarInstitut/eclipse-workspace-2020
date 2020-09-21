package exemples

import java.io.InputStreamReader
import java.io.FileInputStream
import java.io.OutputStreamWriter
import java.io.FileOutputStream
import java.io.File

fun main(args: Array<String>) {
	File("f5.txt").copyTo(File("f5_2.txt"))
	val f_ent = InputStreamReader(FileInputStream("f5.txt"), "UTF-8")
	val f_eix = OutputStreamWriter(FileOutputStream("f5_ISO.txt"), "ISO-8859-1")

	var car = f_ent.read()
	while (car != -1) {
		f_eix.write(car)
		car = f_ent.read()
	}
	f_eix.close()
	f_ent.close()
}