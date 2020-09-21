package exemples

import com.squareup.moshi.*
import java.io.File

class Person(val id: Long, val name: String, val age: Int = -1)

class Persons(val persons: List<Person> = listOf<Person>())


fun main (args: Array<String>){
	val json = File("Persons.json").readText()

    val adapter = Moshi.Builder().build().adapter(Persons::class.java)
   // val adapter: JsonAdapter<Person> = moshi.adapter(Person::class.java)

    val persones = adapter.fromJson(json)
    println(persones.persons.size)
	for (p in persones.persons)
		println(p.name + " (" + p.age  +")")
}

