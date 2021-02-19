package exemples

import net.xqj.exist.ExistXQDataSource

fun main() {
	//val f = File("notes.xml")
	val s = ExistXQDataSource()
	s.setProperty("serverName", "89.36.214.106")
	s.setProperty("port", "8080")
	s.setProperty("user", "admin")
	s.setProperty("password", "ieselcaminas.ad")


	val conn = s.getConnection()
	val sent = "for \$classe in /classe " +
				"return <notes><modul nom=\"{\$classe/assignatura/text()}\">" +
								"{for \$alumne in \$classe//alumne " +
								"order by \$alumne/cognoms " +
								"return <alumne nota=\"{\$alumne/nota/text()}\">" +
									"{concat(\$alumne/nom/text(), \" \", \$alumne/cognoms)}" +
									"</alumne>}</modul></notes> "

	val col = "Tema9"
	val doc = "classe_Kotlin_2.xml"
	val com = "xmldb:store('$col', '$doc', $sent)"
	println(com)
	conn.createExpression().executeCommand(com)
	conn.close()
}