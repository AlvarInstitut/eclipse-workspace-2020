package exemples

import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.io.FileReader

fun main(args: Array<String>) {

	val r_json = FileReader("Empresa.json")

	val arrel = JSONTokener(r_json).nextValue() as JSONObject

	for (e in arrel.getJSONObject("empresa").getJSONArray("empleats")){
		val emp = e as JSONObject		
		println("" + emp.get("nom") + " (" + emp.get("sou") + ")")
	}
}