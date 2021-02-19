package biblioteca

class Llibre {
	var isbn: String? = null
	var titol: String? = null
	var autor: Autor? = null
	var editorial: String? = null
	var prSoci: Soci? = null

	constructor() {
	}

	constructor(isbn: String?) {
		this.isbn = isbn
	}

	constructor(isbn: String?, titol: String?, autor: Autor?, editorial: String?, prSoci: Soci?) {
		this.isbn = isbn
		this.titol = titol
		this.autor = autor
		this.editorial = editorial
		this.prSoci = prSoci
	}
}