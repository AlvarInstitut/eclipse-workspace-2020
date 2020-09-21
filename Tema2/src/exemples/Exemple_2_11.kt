package exemples

import java.io.FileOutputStream

fun main(args: Array<String>) {
	val text = "Contingut per al fitxer."
	val f_out = FileOutputStream("f3.txt",true)
	for (c in text) 
		f_out.write(c.toInt())
	f_out.close()
}