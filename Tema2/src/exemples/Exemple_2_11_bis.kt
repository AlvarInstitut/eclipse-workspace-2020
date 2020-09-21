package exemples

import java.io.File

fun main(args: Array<String>) {
	val text = "Contingut per al fitxer."
	File("f3.txt").writeBytes(text.toByteArray())
}