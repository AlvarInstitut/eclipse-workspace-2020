package exercicis

import java.io.ObjectInputStream
import java.io.FileInputStream
import java.io.IOException
import java.sql.DriverManager

fun main(args: Array<String>) {
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))

	val con = DriverManager.getConnection("jdbc:sqlite:Rutes.sqlite")

	val st = con.createStatement()
	var i = 1
	try {
		while (true) {
			val r = f.readObject() as Ruta
			r.mostrarRuta()
			var sentSQL = "INSERT INTO RUTES VALUES("
			sentSQL += "" + i + ","
			sentSQL += "'" + r.nom + "',"
			sentSQL += "" + r.desnivell + ","
			sentSQL += "" + r.desnivellAcumulat + ")"
			println(sentSQL)
			st.executeUpdate(sentSQL)

			var j = 1
			for (p in r.llistaDePunts) {
				var sentSQL = "INSERT INTO PUNTS VALUES("
				sentSQL += "" + i + "," + j + ","
				sentSQL += "'" + p.nom + "',"
				sentSQL += "" + p.coord.latitud + ","
				sentSQL += "" + p.coord.longitud + ")"
				println(sentSQL)
				st.executeUpdate(sentSQL)
				j++
			}
			i++

		}
	} catch (ex: IOException) {
		f.close()

	}

}