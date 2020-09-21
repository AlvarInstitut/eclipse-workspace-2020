package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
	val url = "jdbc:oracle:thin:@94.177.240.173:1521:orcl"
	val usuari = "scott"
	val password = "tiger"

	val con = DriverManager.getConnection(url, usuari, password)
	System.out.println("Connexió completada")
	con.close()
}