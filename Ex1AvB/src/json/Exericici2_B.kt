package json

import com.squareup.moshi.Moshi
import java.io.FileReader
import java.io.File
import com.squareup.moshi.Types
import com.squareup.moshi.JsonAdapter


class Any(var Dones: Int, var any: String, var Homes: Int)

class Provincia(var Anys: List<Any>, var Provincia: String)

fun main() {
	val json = File("PoblacioSexe.json").readText()

	val moshi = Moshi.Builder().build()
	val llistaTipus = Types.newParameterizedType(List::class.java, Provincia::class.java)
	val adapter: JsonAdapter<List<Provincia>> = moshi.adapter(llistaTipus)
	val provs = adapter.fromJson(json)

	for (p in provs) {
		if (p.Provincia == "Castelló") {
			println(p.Provincia)
			for (a in p.Anys){
				val dif = a.Dones - a.Homes
				println(a.any + ". Dones: " + a.Dones + ". Homes: " + a.Homes + ". Diferència:" + dif)
			}
		}

	}
}