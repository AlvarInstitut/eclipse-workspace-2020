package classes
// Generated 26 d’oct. 2020, 21:22:58 by Hibernate Tools 5.2.12.Final
import java.math.BigDecimal
import java.util.HashSet

/**
 * Poblacio generated by hbm2java
 */
class Poblacio : java.io.Serializable {
	var codM: Int = 0
	var comarca: Comarca? = null
	var nom: String? = null
	var poblacio: Integer? = null
	var extensio: BigDecimal? = null
	var altura: Short? = null
	var longitud: String? = null
	var latitud: String? = null
	var llengua: Character? = null
	var instituts: Set<Institut>? = HashSet<Institut>(0)

	constructor() {}
	constructor(codM: Int, nom: String?) {
		this.codM = codM
		this.nom = nom
	}

	constructor(
		codM: Int, comarca: Comarca?, nom: String?, poblacio: Integer?, extensio: BigDecimal?, altura: Short?,
		longitud: String?, latitud: String?, llengua: Character?, instituts: Set<Institut>?
	) {
		this.codM = codM
		this.comarca = comarca
		this.nom = nom
		this.poblacio = poblacio
		this.extensio = extensio
		this.altura = altura
		this.longitud = longitud
		this.latitud = latitud
		this.llengua = llengua
		this.instituts = instituts
	}
}