package util.bd

import java.sql.Connection
import java.sql.DriverManager

class GestionarRutesBD {
	val con = DriverManager.getConnection("jdbc:sqlite:Rutes.sqlite")

	init {
		val st = con.createStatement()

		var sentSQL = "CREATE TABLE IF NOT EXISTS RUTES (" +
				"num_r INTEGER CONSTRAINT cp_rut PRIMARY KEY," +
				"nom_r TEXT," +
				"desn INTEGER," +
				"desn_ac INTEGER )"
		st.executeUpdate(sentSQL)

		sentSQL = "CREATE TABLE IF NOT EXISTS PUNTS (" +
				"num_r INTEGER CONSTRAINT ce_punt_rut REFERENCES RUTES," +
				"num_p INTEGER," +
				"nom_p TEXT," +
				"latitud DOUBLE," +
				"longitud DOUBLE," +
				"CONSTRAINT cp_punt PRIMARY KEY(num_r,num_p) )"
		st.executeUpdate(sentSQL)

		st.close()
	}

	fun close() {
		con.close()
	}

	fun inserir(r: Ruta) {
		val rs1 = con.createStatement().executeQuery("SELECT MAX(num_r) FROM RUTES")
		rs1.next()
		val n = rs1.getInt(1) + 1
		rs1.close()

		val sentSQL = "INSERT INTO RUTES VALUES(?,?,?,?)"
		val st = con.prepareStatement(sentSQL)
		val sentSQL2 = "INSERT INTO PUNTS VALUES(?,?,?,?,?)"
		val st2 = con.prepareStatement(sentSQL2)
		st.setInt(1, n)
		st.setString(2, r.nom)
		st.setInt(3, r.desnivell!!)
		st.setInt(4, r.desnivellAcumulat!!)
		st.executeUpdate()

		var j = 1
		for (p in r.llistaDePunts) {
			st2.setInt(1, n)
			st2.setInt(2, j)
			st2.setString(3, p.nom)
			st2.setDouble(4, p.coord.latitud)
			st2.setDouble(5, p.coord.longitud)
			st2.executeUpdate()
			j++
		}
		st.close()
		st2.close()
	}

	fun buscar(i: Int): Ruta {
		val rs1 = con.createStatement().executeQuery("SELECT * FROM RUTES WHERE num_r=" + i)
		if (rs1.next()) {
			val llP = arrayListOf<PuntGeo>()
			val rs2 = con.createStatement().executeQuery("SELECT * FROM PUNTS WHERE num_r=" + i)
			while (rs2.next()) {
				llP.add(PuntGeo(rs2.getString(3), Coordenades(rs2.getDouble(4), rs2.getDouble(5))))
			}
			val r = Ruta(rs1.getString(2), rs1.getInt(3), rs1.getInt(4), llP)
			return r
		} else
			return Ruta(null,null,null)
	}

	fun llistat(): ArrayList<Ruta> {
		val ll = arrayListOf<Ruta>()
		val rs = con.createStatement().executeQuery("SELECT num_r FROM RUTES")
		while (rs.next())
			ll.add(buscar(rs.getInt(1)))
		return ll
	}

	fun esborrar(i: Int) {
		con.createStatement().executeUpdate("DELETE FROM PUNTS WHERE num_r=" + i)
		con.createStatement().executeUpdate("DELETE FROM RUTES WHERE num_r=" + i)
	}
	
	fun esborrar(r: Ruta){
		val rs = con.createStatement().executeQuery("SELECT num_r FROM RUTES WHERE nom_r='" + r.nom + "'")
		if (rs.next())
			esborrar(rs.getInt(1))
	}

	fun guardar(r: Ruta) {
		val rs = con.createStatement().executeQuery("SELECT * FROM RUTES WHERE nom_r='" + r.nom + "'")
		if (rs.next()) {
			val sentSQL = "UPDATE RUTES SET desn=?,desn_ac=? WHERE num_r=" + rs.getInt(1)
			val st = con.prepareStatement(sentSQL)
			st.setInt(1, r.desnivell!!)
			st.setInt(2, r.desnivellAcumulat!!)
			st.executeUpdate()

			con.createStatement().executeUpdate("DELETE FROM PUNTS WHERE num_r=" + rs.getInt(1))
			val sentSQL2 = "INSERT INTO PUNTS VALUES(?,?,?,?,?)"
			val st2 = con.prepareStatement(sentSQL2)
			var j = 1
			for (p in r.llistaDePunts) {
				st2.setInt(1, rs.getInt(1))
				st2.setInt(2, j)
				st2.setString(3, p.nom)
				st2.setDouble(4, p.coord.latitud)
				st2.setDouble(5, p.coord.longitud)
				st2.executeUpdate()
				j++
			}
			st.close()
			st2.close()
		} else
			inserir(r)

	}
}