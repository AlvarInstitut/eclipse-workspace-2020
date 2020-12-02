package json

import java.io.FileReader
import org.json.JSONObject
import org.json.JSONTokener
import org.json.JSONArray

fun main() {

	val arrel = JSONTokener(FileReader("VariacioPoblacional.json")).nextValue() as JSONArray
	
	for (p in arrel){
		p as JSONObject
		if (p.get("Nombre")=="Castelló") {
			println("\t" + p.get("Nombre"))
			var acum = 0
			val anys = p.get("Data") as JSONArray
			for (a in anys){
				a as JSONObject
				println(a.get("NombrePeriodo").toString() + " ---> " + a.get("Valor"))
				acum += a.get("Valor").toString().toInt()
			}
			println("Variació total: " + acum)
				
		}
	}

}