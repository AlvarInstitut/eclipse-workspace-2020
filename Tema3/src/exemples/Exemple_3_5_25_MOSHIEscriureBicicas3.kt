package exemples

import com.squareup.moshi.*
import java.io.File

fun main(args: Array<String>) {
	val json = File("Bicicas.json").readText()

	val moshi = Moshi.Builder().build()
	val llistaTipus = Types.newParameterizedType(List::class.java, Estacions::class.java)
	val adapter: JsonAdapter<List<Estacions>> = moshi.adapter(llistaTipus)
	val estacions = adapter.fromJson(json).get(0).ocupacion

	val llistaEstDesti = arrayListOf<EstDesti>()
	for (e in estacions)
		llistaEstDesti.add(EstDesti(e.id.toInt(),e.punto,e.puestos,e.ocupados,e.puestos-e.ocupados))
	
	val llistaTipusDesti = Types.newParameterizedType(List::class.java, EstDesti::class.java)
	val adapter2: JsonAdapter<List<EstDesti>> = moshi.adapter(llistaTipusDesti)
    val json2 = adapter2.toJson(llistaEstDesti)
	
	File("Bicicas3.json").writeText(json2)
}

