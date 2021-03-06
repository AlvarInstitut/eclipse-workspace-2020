package classes
// Generated 15 de nov. 2020, 14:40:02 by Hibernate Tools 5.2.12.Final
import java.util.Date
import java.util.HashSet

/**
 * Vehicle generated by hbm2java
 */
class Vehicle : java.io.Serializable {
	var matricula: String? = null
	var model: String? = null
	var dataC: Date? = null
	var km: Integer? = null
	var practiqueses: Set<Practiques>? = HashSet<Practiques>(0)

	constructor() {}
	constructor(matricula: String?) {
		this.matricula = matricula
	}

	constructor(matricula: String?, model: String?, dataC: Date?, km: Integer?, practiqueses: Set<Practiques>?) {
		this.matricula = matricula
		this.model = model
		this.dataC = dataC
		this.km = km
		this.practiqueses = practiqueses
	}
}