package exist

import net.xqj.exist.ExistXQDataSource

fun main(){
	val s = ExistXQDataSource()
	s.setProperty("serverName","localhost")
	val conn = s.getConnection()
	val sent = "for \$p in //provincia " +
					"let \$s := sum(\$p//valor) " +
					"let \$m := max(\$p//valor) " +
					"let \$a := \$p//año[valor=\$m]/nombrePeriodo/text() " +
					"return concat(\$p/nombre/text(), '\n\tSuma variació: ',\$s,'\n\tVariació Màxima: ',\$m," +
							"'\n\tAny Variació màxima: ',\$a,'\n')"

	val cons = conn.prepareExpression (sent)
	val rs = cons.executeQuery ()

	while (rs.next())
		println(rs.getItemAsString(null))
}