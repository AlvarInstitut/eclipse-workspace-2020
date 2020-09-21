package exemples

import java.io.File

fun main(args: Array<String>) {
	File("f5_2.txt").writeText(File("f5.txt").readText(), Charsets.ISO_8859_1)
}