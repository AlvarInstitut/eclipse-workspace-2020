package classesFactura
// Generated 19 de febr. 2021, 12:15:29 by Hibernate Tools 5.2.12.Final
import java.util.Date
import java.util.HashSet

/**
 * Factura generated by hbm2java
 */
class Factura : java.io.Serializable {
	var numF: Int = 0
	var client: Client? = null
	var data: Date? = null
	var codVen: Integer? = null
	var iva: Byte? = null
	var liniaFacs: Set<LiniaFac>? = HashSet<LiniaFac>(0)

	constructor() {}
	constructor(numF: Int, client: Client?) {
		this.numF = numF
		this.client = client
	}

	constructor(numF: Int, client: Client?, data: Date?, codVen: Integer?, iva: Byte?, liniaFacs: Set<LiniaFac>?) {
		this.numF = numF
		this.client = client
		this.data = data
		this.codVen = codVen
		this.iva = iva
		this.liniaFacs = liniaFacs
	}
}