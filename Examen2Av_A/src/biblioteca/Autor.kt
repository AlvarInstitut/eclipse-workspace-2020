package biblioteca

import java.util.Date

class Autor {
	var codAut: Int = 0
	var nom: String? = null
	var dataN: Date? = null

	constructor() {
	}

	constructor(codAutor: Int) {
		this.codAut = codAutor
	}

	constructor(codAut: Int, nom: String?, dataN: Date?) {
		this.codAut = codAut
		this.nom = nom
		this.dataN = dataN
	}
}