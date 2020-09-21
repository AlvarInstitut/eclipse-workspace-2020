package exercicis

import java.io.DataInputStream
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.ObjectOutputStream

fun main(args: Array<String>){
	val f = DataInputStream(FileInputStream("Rutes.dat"))
	val f2 = ObjectOutputStream(FileOutputStream("Rutes.obj"))
	
	while (f.available()>0){
		val r = Ruta(f.readUTF(),f.readInt(),f.readInt(),arrayListOf<PuntGeo>())
		val n = f.readInt()
		for (i in 0 until n)
			r.addPunt(PuntGeo(f.readUTF(),Coordenades(f.readDouble(),f.readDouble())))
		r.mostrarRuta()
		f2.writeObject(r)
	}
	f.close()
	f2.close()
}