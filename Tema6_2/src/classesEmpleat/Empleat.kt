package classesEmpleat

class Empleat (
	var nif: String? , var nom: String? , var departament: Int? , var edat: Int? , var sou: Double? ,
	var foto: Array<Byte>? ,
	var curriculum: Array<Char>? ,
	var adreca: Adreca? ,
	var correus_e: Array<String>? ,
	var telefons : ArrayList<Telefon>?
) {
	constructor(nif: String) : this(nif,null,null,null,null,null,null,null,null,null) 
}

