package exemples

class Empleat (
	var nif: String? ,
	var nom: String? ,
	var departament: Int? ,
	var edat: Int? = 0 ,
	var sou: Double? = 0.0 ,
	var foto: Array<Byte>? ,
	var curriculum: Array<Char>? ,
	var adreca: Adreca? ,
	var correus_e: Array<String>? ,
	var telefons : Array<Telefon>?
) {
}