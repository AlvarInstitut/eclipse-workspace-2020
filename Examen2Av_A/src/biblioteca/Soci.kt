package biblioteca

import java.util.ArrayList
import java.util.Date

class Soci {
	var numSoci: Int = 0
	var dni: String? = null
	var nom: String? = null
	var adreca: String? = null
	var cp: Int = 0
	var poblacio: String? = null
	var telefon: String? = null
	var dataN: Date? = null
	var dataS: Date? = null
	var llPrestecs: ArrayList<Llibre>? = null

	constructor() {
	}

	constructor(numSoci: Int) {
		this.numSoci = numSoci
	}

	constructor(
		numSoci: Int, dni: String?, nom: String?, adreca: String?, cp: Int, poblacio: String?, telefon: String?,
		dataN: Date?, dataS: Date?, llPrestecs: ArrayList<Llibre>?
	) {
		this.numSoci = numSoci
		this.dni = dni
		this.nom = nom
		this.adreca = adreca
		this.cp = cp
		this.poblacio = poblacio
		this.telefon = telefon
		this.dataN = dataN
		this.dataS = dataS
		this.llPrestecs = llPrestecs
	}

	constructor(
		numSoci: Int, dni: String?, nom: String?, adreca: String?, cp: Int, poblacio: String?, telefon: String?,
		dataN: Date?, dataS: Date?
	) {
		this.numSoci = numSoci
		this.dni = dni
		this.nom = nom
		this.adreca = adreca
		this.cp = cp
		this.poblacio = poblacio
		this.telefon = telefon
		this.dataN = dataN
		this.dataS = dataS
	}
}