package jdbc

import java.sql.DriverManager

fun main(){
	val con = DriverManager.getConnection("jdbc:postgresql://89.36.214.106/autoescola","autoescola","autoescola")
	val sql = "SELECT nom, COUNT(data) , SUM(km) " +
					"FROM ALUMNE INNER JOIN PRACTIQUES USING (dni) " +
					"GROUP BY 1 " +
					"HAVING COUNT(data) >= 5 " +
					"ORDER BY 1"
	val rs = con.createStatement().executeQuery(sql)
	while (rs.next()){
		println ("" + rs.getString(1) + "\t(" + rs.getInt(2) + " ---> " + rs.getInt(3) + ")")
	}
	rs.close()
	con.close()
}