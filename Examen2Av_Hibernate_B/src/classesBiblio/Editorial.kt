package classesBiblio
// Generated 20 de febr. 2021, 19:14:36 by Hibernate Tools 5.2.12.Final
import java.util.HashSet

/**
 * Editorial generated by hbm2java
 */
class Editorial : java.io.Serializable {
	var codi: String? = null
	var nom: String? = null
	var web: String? = null
	var llibres: Set<Llibre>? = HashSet<Llibre>(0)

	constructor() {}
	constructor(codi: String?) {
		this.codi = codi
	}

	constructor(codi: String?, nom: String?, web: String?, llibres: Set<Llibre>?) {
		this.codi = codi
		this.nom = nom
		this.web = web
		this.llibres = llibres
	}
}