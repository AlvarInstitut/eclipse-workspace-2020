package exemples

import java.io.PrintStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
	val f_out = PrintStream("f6.txt")

	val a = 5.25.toFloat()
	val b = "Hola."
	f_out.print(b)
	f_out.println("Què tal?")
	f_out.println(a + 3)
	f_out.printf("El número %d en hexadecimal és %x", 27, 27)

	f_out.close();
}