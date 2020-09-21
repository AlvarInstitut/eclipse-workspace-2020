package exercicis

import java.io.File
import java.io.BufferedReader
import java.io.InputStreamReader
import java.text.SimpleDateFormat

fun main(a: Array<String>) {
	var f = File.listRoots()[0]
	var opcio = 0
	while (opcio != -1) {
		llistaDirectori(f)
		println()
		println("Introdueix un número (-1 per acabar):")
		opcio = Integer.parseInt(BufferedReader(InputStreamReader(System.`in`)).readLine());
		if (opcio == 0) {
			val f1 = f.getParentFile()
			if (f1 == null)
				println("Ja estàs a l'arrel")
			else
				f = f1

		} else if (opcio > 0 && opcio <= f.listFiles().size) {

			val f1 = f.listFiles().sorted()[opcio - 1]
			println(f1.getName())
			if (f1.exists()) {
				if (f1.isDirectory())
					if (f1.canRead())
						f = f1;
					else
						println("No tens permís de lectura");
				else
					println("No és un directori");
			} else
				println("No existeix el directori");
		}
	}
}

fun llistaDirectori(f: File) {
	val s = "Llista de fitxers i directoris del directori " + f.getCanonicalPath()
	println(s)
	println("-".repeat(s.length))
	println("0.- Directori pare")
	var i = 1
	for (e in f.listFiles().sorted()) {
		print("" + i + ".- ")
		if (e.isFile())
			println(e.getName() + "\t " + e.length())
		if (e.isDirectory())
			println(e.getName() + "\t <Directori>")
		i += 1

	}
}