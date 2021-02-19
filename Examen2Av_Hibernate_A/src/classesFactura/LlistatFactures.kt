package classesFactura

fun main(){
	val sF = SessionFactoryUtil.sessionFactory
	val sessio = sF.openSession()
	
	val q = sessio.createQuery("from Factura")
	val factures = q.list()
	
	for(f in factures as List<Factura>) {
		
		print("Factura número " + f.numF + ". Client: " + f.client?.nom )
		var tot = 0.0
		for (l in f.liniaFacs!!)
			tot += l.quant?.toDouble()!! * l.preu?.toDouble()!!
		println (". Total factura: " + tot)
	}
	sessio.close()
}