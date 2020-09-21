package exemples

import java.io.FileInputStream

fun main(args: Array<String>) {
	val f_in = FileInputStream("f2.txt")
	var buffer = ByteArray(30)
	var n = f_in.read(buffer)
	while (n != -1) {
		for (i in 0..n - 1)
			print(buffer[i].toChar())
		println("")
		n = f_in.read(buffer)
	}
	f_in.close();
}