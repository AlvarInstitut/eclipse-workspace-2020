package exercicis

import java.io.ObjectInputStream
import java.io.FileInputStream
import java.io.IOException
import java.sql.DriverManager

fun main(args: Array<String>) {
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))

	val con = DriverManager.getConnection("jdbc:sqlite:Rutes.sqlite")
	val sentSQL = "INSERT INTO RUTES VALUES(?,?,?,?)"
	val st = con.prepareStatement(sentSQL)
	val sentSQL2 = "INSERT INTO PUNTS VALUES(?,?,?,?,?)"
	val st2 = con.prepareStatement(sentSQL2)
	var i = 1
	try {
		while (true) {
			val r = f.readObject() as Ruta
			r.mostrarRuta()
			st.setInt(1, i)
			st.setString(2, r.nom)
			st.setInt(3, r.desnivell)
			st.setInt(4, r.desnivellAcumulat)
			st.executeUpdate()

			var j = 1
			for (p in r.llistaDePunts) {
				st2.setInt(1, i)
				st2.setInt(2, j)
				st2.setString(3, p.nom)
				st2.setDouble(4, p.coord.latitud)
				st2.setDouble(5, p.coord.longitud)
				st2.executeUpdate()
				j++
			}
			i++

		}
	} catch (ex: IOException) {
		f.close()

	}

}