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

	init {

		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
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

		val serviceAccount = FileInputStream("acces-a-dades-6e5a6-firebase-adminsdk-ei7uc-fcf7da56aa.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build()

		FirebaseApp.initializeApp(options)
		val database = FirestoreClient.getFirestore()
		val docRef = database.collection("Xats").document("XatProva")
		
		database.collection("Agenda").orderBy("data")?.addSnapshotListener { snapshots, e ->
			listModel.removeAllElements()
			if (e != null) {
				System.err.println("Listen failed: " + e)
				return@addSnapshotListener
			}

			for (dc in snapshots!!.getDocumentChanges()) {
				when (dc.getType()) {
					DocumentChange.Type.ADDED -> {
						val doc = dc.getDocument()
						val d = SimpleDateFormat("dd-MM-yyyy HH:mm").format(doc.getDate("data"))
						listModel.addElement(d + ": " + doc.getString("nom") + "\n")
					}

					DocumentChange.Type.MODIFIED ->
						println("Missatge modificat: " + dc.getDocument().getData());

					DocumentChange.Type.REMOVED ->
						println("Missatge esborrat: " + dc.getDocument().getData());
				}
			}
		
		}


		list.addListSelectionListener() {
			if (list.getSelectedIndex() >= 0)
				visualitzaEsdeveniment(list.getSelectedValue())
		}
	}


	fun visualitzaEsdeveniment(estacio: String) {
		// Instruccions per a mostrar les característiques en el area, el JTextArea de la dreta
		area.setText("")

	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		FinestraAgenda().isVisible = true
	}
}

