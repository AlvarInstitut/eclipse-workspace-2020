package classes
// Generated 15 de nov. 2020, 14:40:02 by Hibernate Tools 5.2.12.Final
import java.util.Date

/**
 * ExamenId generated by hbm2java
 */
class ExamenId : java.io.Serializable {
	var dni: String? = null
	var data: Date? = null

	constructor() {}
	constructor(dni: String?, data: Date?) {
		this.dni = dni
		this.data = data
	}

	fun equals(other: ExamenId?): Boolean {
		if ((this === other))
			return true
		if ((other == null))
			return false
		if (!(other is ExamenId))
			return false
		val castOther = other as ExamenId?
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