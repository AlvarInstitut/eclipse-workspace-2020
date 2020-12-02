package jdbc

import java.sql.DriverManager

fun main(){
	val con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106/autoescola","autoescola","autoescola")
	val rs = con.createStatement().executeQuery("SELECT * FROM ALUMNE WHERE baixa='No' ORDER BY data_n DESC")
	while (rs.next()){
		println ("Dni: " + rs.getString(1))
		println ("Nom: " + rs.getString(2))
		println ("Data naixement: " + rs.getString(3))
		println ("Baixa: " + rs.getString(8))
		println()
	}
	rs.close()
	con.close()
}