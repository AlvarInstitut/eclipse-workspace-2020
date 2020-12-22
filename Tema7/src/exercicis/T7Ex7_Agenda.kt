package exercicis

import javax.swing.*
import java.awt.*
import java.net.URL
import java.io.InputStreamReader
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import com.google.cloud.firestore.DocumentChange
import java.text.SimpleDateFormat

class FinestraAgenda : JFrame() {

	var listModel = DefaultListModel<String>()
	var list = JList(listModel)
	var area = JTextArea(5, 15)
	val eixir =JButton("Eixir")

	init {

		defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
		setTitle("Firebase: Agenda")
		setSize(800, 800)
		setLayout(BorderLayout())

		val panell1 = JPanel(FlowLayout())
		val panell2 = JPanel(GridLayout(1, 2))
		add(panell1, BorderLayout.NORTH)
		add(panell2, BorderLayout.CENTER)
		list.setForeground(Color.blue)
		var scroll1 = JScrollPane(list)
		var scroll2 = JScrollPane(area)
		panell2.add(scroll1)
		panell2.add(scroll2)

		panell1.add(JLabel("AGENDA"))
		
		add(eixir,BorderLayout.SOUTH)

		val serviceAccount = FileInputStream("acces-a-dades-6e5a6-firebase-adminsdk-ei7uc-fcf7da56aa.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build()

		FirebaseApp.initializeApp(options)
		val database = FirestoreClient.getFirestore()
		//database.collection("Agenda")
		//val docRef = database.collection("Xats").document("XatProva")
		// Exemple de lectura única: senzillament sobre un ApiFuture i sobre ell get()
		// Per a posar el títol. Sobre /Xats/XatProva/nomXat
		//val nomXat = future.get().getString("nomXat")
		//this.setTitle(nomXat)
		list.addListSelectionListener() {
			if (list.getSelectedIndex() >= 0) {
				area.setText("")
				val llistaCamps = list.getSelectedValue().split(":")
				println(llistaCamps[0])
				val docRef = database.collection("Xats").whereEqualTo("data", llistaCamps[0]).get().get()
				for (doc in docRef!!)
					println(doc.getString("nom"))
			}
		}

		database.collection("Agenda").orderBy("data").addSnapshotListener { snapshots, e ->
			//listModel.removeAllElements()
			if (e != null) {
				println("Listen failed: " + e)
				return@addSnapshotListener
			}
			if (snapshots != null) {
				for (dc in snapshots.getDocumentChanges()) {
					when (dc.getType()) {
						DocumentChange.Type.ADDED -> {
							val doc = dc.getDocument()
							//listModel.addElement(doc.getDate("data").toString() + ": " + doc.getString("nom"))
							val d = SimpleDateFormat("dd-MM-yyyy HH:mm").format(doc.getDate("data"))
							listModel.addElement(d + ": " + doc.getString("nom"))
							//listModel.addElement(doc.getDate("data").toString() + ": " + doc.getString("nom"))
						}

						DocumentChange.Type.MODIFIED ->
							println("Missatge modificat: " + dc.getDocument().getData());

						DocumentChange.Type.REMOVED ->
							println("Missatge esborrat: " + dc.getDocument().getData());
					}
				}
			}
			else
				println("No hi ha documents")

		}

		eixir.addActionListener(){
			database.close()
			System.exit(0)
		}
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		FinestraAgenda().isVisible = true
	}
}

