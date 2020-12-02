package json

import java.io.File
import org.json.JSONArray
import org.json.JSONObject
import java.io.FileWriter

fun main(args: Array<String>) {
	val linies = File("PoblacioSexeOrdenat.csv").readLines()
	val arrel = JSONArray()
	var p = JSONObject()
	var anys = JSONArray()
	var item = JSONObject()
	var provAnt = ""
	for (l in linies) {
		val camps = l.split(";")
		if (camps.get(1) != provAnt) {
			p = JSONObject()
			arrel.put(p)
			p.put("Provincia", camps.get(1))
			anys = JSONArray()
			p.put("Anys", anys)
			provAnt=camps.get(1)
		}
		if (camps.get(0) == "Hombres") {
			item = JSONObject()
			item.put("any", camps.get(2))
			item.put("Homes", camps.get(3))
		} else {
			item.put("Dones", camps.get(3))
			anys.put(item)
		}

	}
	val f = FileWriter("PoblacioSexe.json")
	f.write(arrel.toString(4))
	f.close()
}
