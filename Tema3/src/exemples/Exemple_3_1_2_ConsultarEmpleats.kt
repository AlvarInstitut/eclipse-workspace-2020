package exemples


import java.io.FileInputStream
import java.io.DataInputStream

fun main(args: Array<String>) {
	val f = DataInputStream(FileInputStream("Empleats.dat"))

	while (f.available() > 0) {
		println("Número: " + f.readInt())
		println("Nom: " + f.readUTF())
		println("Depart: " + f.readInt())
		println("Edat: " + f.readInt())
		println("Sou: " + f.readDouble())
		println()
	}
	f.close()
}