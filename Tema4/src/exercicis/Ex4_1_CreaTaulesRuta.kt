package exercicis

import java.sql.DriverManager

fun main(args: Array<String>){
	val con = DriverManager.getConnection("jdbc:sqlite:Rutes.sqlite")
	val st = con.createStatement()
	
	var sentSQL = "CREATE TABLE RUTES (" +
						"num_r INTEGER CONSTRAINT cp_rut PRIMARY KEY," +
						"nom_r TEXT," +
						"desn INTEGER," +
						"desn_ac INTEGER )"
	st.executeUpdate(sentSQL)
	
	sentSQL = "CREATE TABLE PUNTS (" +
						"num_r INTEGER CONSTRAINT ce_punt_rut REFERENCES RUTES," +
						"num_p INTEGER," +
						"nom_p TEXT," +
						"latitud DOUBLE," +
						"longitud DOUBLE," +
						"CONSTRAINT cp_punt PRIMARY KEY(num_r,num_p) )"
	st.executeUpdate(sentSQL)
	
	st.close()
	con.close()
	
}