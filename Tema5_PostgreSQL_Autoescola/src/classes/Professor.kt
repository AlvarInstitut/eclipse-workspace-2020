package classes
// Generated 15 de nov. 2020, 14:40:02 by Hibernate Tools 5.2.12.Final
import java.util.Date
import java.util.HashSet

/**
 * Professor generated by hbm2java
 */
class Professor : java.io.Serializable {
	var dni: String? = null
	var nom: String? = null
	var dataI: Date? = null
	var alumnes: Set<Alumne>? = HashSet<Alumne>(0)

	constructor() {}
	constructor(dni: String?) {
		this.dni = dni
	}

	constructor(dni: String?, nom: String?, dataI: Date?, alumnes: Set<Alumne>?) {
		this.dni = dni
		this.nom = nom
		this.dataI = dataI
		this.alumnes = alumnes
	}
}