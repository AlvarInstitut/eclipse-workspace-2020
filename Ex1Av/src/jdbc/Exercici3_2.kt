package jdbc

import java.sql.DriverManager

fun main(){
	val con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106/factura","factura","factura")
	val sql = "SELECT CATEGORIA.cod_cat, descripcio, count(cod_a),avg(preu) " +
				 " FROM CATEGORIA INNER JOIN ARTICLE USING(cod_cat) " +
				 " GROUP BY CATEGORIA.cod_cat, descripcio " +
				 " ORDER BY 1"
	val rs = con.createStatement().executeQuery(sql)
	
	while (rs.next()){
		println(rs.getString(1) + ": " + rs.getString(2))
		println("\tNúmero d'articles: " + rs.getInt(3))
		println("\tMitjana de preus: " + rs.getFloat(4))
		println()
	}
	rs.close()
	con.close()
}