package exemples

import java.io.File

fun main(args: Array<String>){
	val tot = File("f1.txt").readText()
	for(c in tot){
		println(c)
	}
}