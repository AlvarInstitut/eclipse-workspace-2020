package classes
// Generated 15 de nov. 2020, 14:40:02 by Hibernate Tools 5.2.12.Final
import java.util.Date

/**
 * PractiquesId generated by hbm2java
 */
class PractiquesId : java.io.Serializable {
	var dni: String? = null
	var data: Date? = null

	constructor() {}
	constructor(dni: String?, data: Date?) {
		this.dni = dni
		this.data = data
	}

	fun equals(other: PractiquesId?): Boolean {
		if ((this === other))
			return true
		if ((other == null))
			return false
		if (!(other is PractiquesId))
			return false
		val castOther = other as PractiquesId?
		return ((((this.dni === castOther!!.dni) || (this.dni != null && castOther!!.dni != null && this.dni!!.equals(
			castOther!!.dni
		)))) && (((this.data === castOther!!.data) || ((this.data != null && castOther!!.data != null
				&& this.data!!.equals(castOther!!.data))))))
	}

	override fun hashCode(): Int {
		var result = 17
		result = 37 * result + (if (dni == null) 0 else this.dni!!.hashCode())
		result = 37 * result + (if (data == null) 0 else this.data!!.hashCode())
		return result
	}
}