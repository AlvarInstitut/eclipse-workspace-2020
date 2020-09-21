package exemples

import java.io.CharArrayReader

fun main(args: Array<String>) {
	val ent_1 = "Aquest és un byte array"
	val f_in = CharArrayReader(ent_1.toCharArray())
	var c = f_in.read()
	while (c != -1) {
		println(c.toChar())
		c = f_in.read()
	}
	f_in.close()
}