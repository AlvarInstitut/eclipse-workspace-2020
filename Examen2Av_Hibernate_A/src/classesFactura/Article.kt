package classesFactura
// Generated 19 de febr. 2021, 12:15:29 by Hibernate Tools 5.2.12.Final
import java.math.BigDecimal
import java.util.HashSet

/**
 * Article generated by hbm2java
 */
class Article : java.io.Serializable {
	var codA: String? = null
	var descrip: String? = null
	var preu: BigDecimal? = null
	var stock: Short? = null
	var stockMin: Short? = null
	var liniaFacs: Set<LiniaFac>? = HashSet<LiniaFac>(0)

	constructor() {}
	constructor(codA: String?) {
		this.codA = codA
	}

	constructor(
		codA: String?,
		descrip: String?,
		preu: BigDecimal?,
		stock: Short?,
		stockMin: Short?,
		liniaFacs: Set<LiniaFac>?
	) {
		this.codA = codA
		this.descrip = descrip
		this.preu = preu
		this.stock = stock
		this.stockMin = stockMin
		this.liniaFacs = liniaFacs
	}
}