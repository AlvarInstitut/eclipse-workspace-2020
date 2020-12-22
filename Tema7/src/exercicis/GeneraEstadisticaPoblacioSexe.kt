package exercicis

import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.io.FileReader
import com.google.firebase.cloud.FirestoreClient
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp


fun main(args: Array<String>) {
	var dades = HashMap<String, Any>()


	val r_json = FileReader("PoblacioSexe.json")

	val arrel = JSONTokener(r_json).nextValue() as JSONArray

	val serviceAccount = FileInputStream("xat-ad-firebase-adminsdk-my2d0-8c69944b34.json")

	val options = FirebaseOptions.Builder()
		.setCredentials(GoogleCredentials.fromStream(serviceAccount))
		.build()

	FirebaseApp.initializeApp(options)
	val database = FirestoreClient.getFirestore()
	val col = database.collection("Estadistica")


	for (e in arrel) {
		e as JSONObject
		println(e.get("Provincia"))
		val anys = e.get("Anys") as JSONArray
		for (a in anys) {
			a as JSONObject
			println("\t" + a.get("any") + ": " + a.get("Dones") + " - " + a.get("Homes"))
			dades = HashMap<String, Any>()

			dades.put("any", a.get("any"))
			dades.put("Dones", a.get("Dones"))
			dades.put("Homes", a.get("Homes"))
			dades.put("Provincia", e.get("Provincia")) 
			//a.put("Provincia",e.get("Provincia"))
			val f = col.add(dades)
			println(f.get())

		}
		println()
	}


}