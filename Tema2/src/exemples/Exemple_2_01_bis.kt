package exemples

import java.io.File

fun main(args: Array<String>){
	val f = File("f1.txt")
	val tot = f.readBytes()
	for (c in tot){
		println(c.toChar())
	}
}