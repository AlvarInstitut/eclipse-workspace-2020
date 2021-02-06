package exemples

import net.xqj.exist.ExistXQDataSource

fun main() {
	val s = ExistXQDataSource()
	s.setProperty("serverName", "89.36.214.106")
	s.setProperty("port", "8080")
	//s.setProperty("user", "admin")
	//s.setProperty("password", "ieselcaminas.ad")

	val conn = s.getConnection()
	System.out.println("Connexió feta")
	val sent = "//ruta"

	val cons = conn . prepareExpression (sent)
	val rs = cons . executeQuery ()

	while (rs.next())
		println(rs.getItemAsString(null))
	
	
}