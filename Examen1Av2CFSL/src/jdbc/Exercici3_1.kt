package jdbc

import java.sql.DriverManager

fun main(args: Array<String>) {
	val con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106/factura", "factura", "factura")
	val st = con.createStatement()
	val rs = st.executeQuery("SELECT * FROM CLIENT ORDER BY cod_pob desc, nom")
	
	while (rs.next()){
		println("Població: " + rs.getInt(5))
		println("Número: " + rs.getInt(1) )
		println("Nom: " + rs.getString(2))
		println()
	}

	rs.close()
	st.close()
	con.close()
}