package exemples

import net.xqj.exist.ExistXQDataSource

fun main() {
	val s = ExistXQDataSource()
	s.setProperty("serverName", "89.36.214.106")
	s.setProperty("port", "8080")
	s.setProperty("user", "admin")
	s.setProperty("password", "ieselcaminas.ad")
	val conn = s.getConnection()
	val exp = conn.createExpression()
	exp.executeCommand("xmldb:store(\"Tema9\", \"FitxerKotlin.xml\", \"<Hola/>\")")
	conn.close()
}