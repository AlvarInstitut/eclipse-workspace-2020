package exemples

import java.io.FileInputStream
import java.io.FileOutputStream

fun main(args: Array<String>) {
	val f_in = FileInputStream("f2.txt")
	val f_out = FileOutputStream("f4.txt")

	var buffer = ByteArray(30)
	var num = f_in.read(buffer)
	while (num != -1) {
		f_out.write(buffer,0,num)      // ara sí que funcionarà bé
		num = f_in.read(buffer)
	}
	f_in.close();
	f_out.close();
}