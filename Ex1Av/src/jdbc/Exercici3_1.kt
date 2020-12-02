package jdbc

import java.sql.DriverManager

fun main(){
	val con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106/factura","factura","factura")
	val rs = con.createStatement().executeQuery("SELECT * FROM CLIENT ORDER BY cod_pob DESC, nom")
	
	while (rs.next()){
		println("Codi Poblacio: " + rs.getInt(5))
		println("Codi Client: " + rs.getInt(1))
		println("Nom: " + rs.getString(2))
		println()
	}
	rs.close()
	con.close()
}