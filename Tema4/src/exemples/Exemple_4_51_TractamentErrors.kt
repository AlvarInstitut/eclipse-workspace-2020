package exemples

import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

fun main(args: Array<String>) {

	var connectat = false
	var con: Connection? = null
	println("tractamentErrorConnexio()")

	try {
		// Carrega el driver en memòria. Si no es troba el driver, saltarà l'excepció ClassNotFoundException
		Class.forName("com.mysql.jdbc.Driver")

		val url = "jdbc:mysql://89.36.214.106:3306/geo"

		val usuari = "geo"
		val contrasenyes = arrayOf("geo0", "geo1", "geo")

		for (i in 0 until contrasenyes.size) {
			try {
				con = DriverManager.getConnection(url, usuari, contrasenyes[i])
				connectat = true
				break
			} catch (ex: SQLException) {
				if (!ex.getSQLState().equals("28000")) {
					// NO és un error d'autenticació
					throw ex
				}
			}
		}
		if (connectat)
			println("Connexió efectuada correctament")
		else
			println("Error en la contrasenya")
	} catch (ex: SQLException) {
		if (ex.getSQLState().equals("08001")) {
			println(
				"S'ha detectat un problema de connexió. Reviseu els cables de xarxa i assegureu-vos que el SGBD està operatiu."
						+ " Si continua sense connectar, aviseu el servei tècnic"
			)

		} else {
			println(
				"S'ha produït un error inesperat. Truqueu al servei tècnic indicant el següent codi d'error SQL:"
						+ ex.getSQLState()
			)
		}
	} catch (ex: ClassNotFoundException) {
		println("No s'ha trobat el controlador JDBC (" + ex.message + "). Truqueu al servei tècnic")
	} finally {
		try {
			if (con != null && !con.isClosed()) {
				con.close()
			}
		} catch (ex: SQLException) {
			throw ex
		}
	}
}
