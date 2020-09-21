package exemples

import java.sql.DriverManager

fun main(args: Array<String>) {
	val url = "jdbc:oracle:thin:@94.177.240.173:1521:orcl"
	val usuari = "scott"
	val password = "tiger"

	val con = DriverManager.getConnection(url, usuari, password)

	val st = con.createStatement()
	val rs = st.executeQuery("SELECT * FROM emp")
	while (rs.next()) {
		print("" + rs.getInt(1) + "\t")
		println(rs.getString(2))
	}
	st.close()

	con.close()
}