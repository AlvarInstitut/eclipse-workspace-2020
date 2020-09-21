package exemples

class Empleat2{
	var nif: String = "" 
	var nom: String = ""
	var departament: Int= 0
	var edat: Int? = 0 
	var sou: Double? = 0.0 
	var foto: Array<Byte>? = null
	var curriculum: Array<Char>? = null
	var adreca: Adreca? = null
	var correus_e: Array<String>? = null
	var telefons : Array<Telefon> ?= null
 
	constructor (nif:String){
		this.nif=nif
	}
}