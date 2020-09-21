package exemples

import java.io.RandomAccessFile

fun main(args: Array<String>) {
	val f = RandomAccessFile("Empleats.dat", "r");
	f.seek(56);
	System.out.println("Núm.: " + f.readInt());
	System.out.println("Nom: " + f.readUTF());
	System.out.println("Depart: " + f.readInt());
	System.out.println("Edat: " + f.readInt());
	System.out.println("Sou: " + f.readDouble());
	f.close();
}