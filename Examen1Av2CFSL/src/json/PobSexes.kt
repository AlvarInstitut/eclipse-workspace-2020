package json

import java.io.FileReader
import org.json.JSONTokener
import org.json.JSONArray
import org.json.JSONObject

fun main(args: Array<String>) {

	val r_json = FileReader("PoblacioSexe.json")
	val arrel = JSONTokener(r_json).nextValue() as JSONArray
	
	for (pr in arrel) {
		pr as JSONObject
		if (pr.get("Provincia")=="Castelló") {
			println(pr.get("Provincia"))
			var acum = 0
			val anys = pr.get("Anys") as JSONArray
			for (a in anys){
				a as JSONObject
				val dif = a.get("Dones").toString().toInt()-a.get("Homes").toString().toInt()
				acum += dif
				print ("" + a.get("any") + ". Dones: " + a.get("Dones") + ". Homes: " + a.get("Homes"))
				println(". Diferència: " + dif)
			}
			println("Diferència acumulada: " + acum)
		}
	}
}