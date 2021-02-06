package exemples

import net.xqj.exist.ExistXQDataSource

fun main() {
	val s = ExistXQDataSource()
	s.setProperty("user", "admin")
	s.setProperty("password", "admin")
	val conn = s.getConnection()
	val exp = conn.createExpression()
	exp.executeCommand("xmldb:store(\"Tema9\", \"FitxerKotlin.xml\", \"<Hola/>\")")
	conn.close()
}