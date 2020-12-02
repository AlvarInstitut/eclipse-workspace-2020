package json

import java.io.FileReader
import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray

fun main() {
	val r_json = FileReader("PoblacioSexe.json")

	val arrel = JSONTokener(r_json).nextValue() as JSONArray

	for (p in arrel) {
		p as JSONObject
		if (p.get("Provincia") == "Castelló") {
			println(p.get("Provincia"))
			var acum = 0
			for (a in p.getJSONArray("Anys")) {
				a as JSONObject
				print("" + a.get("any") + ". ")
				print("Dones: " + a.get("Dones") + ". ")
				print("Homes: " + a.get("Homes") + ". ")
				val dif = a.get("Dones").toString().toInt() - a.get("Homes").toString().toInt()
				println("Diferència: " + dif + ". ")
				acum += dif
			}
			println("Diferència acumulada: " + acum)
		}
	}
}