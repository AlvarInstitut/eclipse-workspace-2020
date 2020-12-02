package json

import java.io.FileReader
import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray

fun main(args: Array<String>) {

	val r_json = FileReader("VariacioPoblacional.json")
	val arrel = JSONTokener(r_json).nextValue() as JSONArray
	for (e in arrel) {
		e as JSONObject
		if (e.get("Nombre") == "Castelló") {
			println(e.get("Nombre"))
			val dades = e.get("Data") as JSONArray
			var acum = 0
			for (a in dades){
				a  as JSONObject
				println("" + a.get("NombrePeriodo") + " ---> " + a.get("Valor"))
				acum += a.get("Valor").toString().toInt()
			}
			println("Variació total: " + acum)
		}
	}
}