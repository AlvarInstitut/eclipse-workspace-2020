package exemples

import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.io.FileReader
import java.io.FileWriter

fun main(args: Array<String>) {

	val r_json = FileReader("Bicicas.json")
	val arrel = JSONTokener(r_json).nextValue() as JSONArray
	val estacions = arrel.getJSONObject(0).getJSONArray("ocupacion")

	val destEstacions = JSONArray()	

	for (e in estacions){
		e as JSONObject
		val destE = JSONObject();
		destE.put("num", e.get("id"));
		destE.put("nom", e.get("punto"));
		destE.put("llocs", e.get("puestos"));
		destE.put("ocupats", e.get("ocupados"));
		val lliures = e.get("puestos") as Int - e.get("ocupados") as Int
		destE.put("lliures", lliures)
		destEstacions.put(destE)
	}

	val bicicas = JSONObject()
	bicicas.put("bicicas", destEstacions)

	val w_json = FileWriter("bicicas2.json");
	w_json.write(bicicas.toString(4));
	w_json.close();
}