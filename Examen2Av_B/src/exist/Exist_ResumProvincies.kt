package exist

import net.xqj.exist.ExistXQDataSource

fun main(){
	val s = ExistXQDataSource()
	s.setProperty("serverName","localhost")
	val conn = s.getConnection()
	val sent = "for \$p in //Provincia " +
					"let \$d := avg(\$p//Dones) " +
					"let \$h := avg(\$p//Homes) " +					"let \$m := max(\$p//valor) " +
					"let \$q := count(\$p//Any[number(Dones) < number(Homes)]) " +
					"return concat(\$p/Nom/text(), '\n\tMitjana Dones: ',\$d,'\n\tMitjana Homes: ',\$h," +
							"'\n\tAnys amb menys dones que homes: ',\$q,'\n')"

	val cons = conn.prepareExpression (sent)
	val rs = cons.executeQuery ()

	while (rs.next())
		println(rs.getItemAsString(null))
}