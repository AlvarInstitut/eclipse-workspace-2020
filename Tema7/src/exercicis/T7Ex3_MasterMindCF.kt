package exercicis

import java.awt.EventQueue
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JTextArea
import javax.swing.JButton
import javax.swing.JTextField
import javax.swing.JPanel
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.Color
import javax.swing.JScrollPane
import java.io.FileInputStream

import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import java.util.Date
import com.google.firebase.cloud.FirestoreClient

class JugadaCF(var nom: String, var tirada: String, var colocades: String, var desordenades: String, var data: Date)

class MastermindCF : JFrame() {

	val etJugador = JLabel("Nom Jugador:")
	val jugador = JTextField(25)

	val etiqueta = JLabel("Jugades:")
	val area = JTextArea()

	val etIntroduccioJugada = JLabel("Introdueix jugada:")
	val enviar = JButton("Enviar")
	val tirada = JTextField(15)

	val novaPartida = JButton("Nova Partida")

	var secret = ""
	var docNom = ""
	var secMaster = ""
	var finalitzada = false

	// en iniciar posem un contenidor per als elements anteriors
	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setBounds(100, 100, 450, 300)
		setLayout(BorderLayout())
		// contenidor per als elements
		//Hi haurà títol. Panell de dalt: últim missatge. Panell de baix: per a introduir missatge. Panell central: tot el xat

		val panell1 = JPanel(FlowLayout())
		panell1.add(etJugador)
		panell1.add(jugador)
		getContentPane().add(panell1, BorderLayout.NORTH)

		val panell2 = JPanel(BorderLayout())
		panell2.add(etiqueta, BorderLayout.NORTH)
		area.setForeground(Color.blue)
		area.setEditable(false)
		val scroll = JScrollPane(area)
		panell2.add(scroll, BorderLayout.CENTER)
		getContentPane().add(panell2, BorderLayout.CENTER)

		val panell3 = JPanel(FlowLayout())
		panell3.add(etIntroduccioJugada)
		panell3.add(tirada)
		panell3.add(enviar)
		novaPartida.setVisible(false)
		panell3.add(novaPartida)
		getContentPane().add(panell3, BorderLayout.SOUTH)

		setVisible(true)
		enviar.addActionListener { enviar() }

		novaPartida.addActionListener { novaPartida() }

		// Connexió a Cloud Firestore. Inicailització de la referència a Mastermind
		val serviceAccount = FileInputStream("xat-ad-firebase-adminsdk-my2d0-8c69944b34.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build()

		FirebaseApp.initializeApp(options)

		val db = FirestoreClient.getFirestore()
		val mm = db.collection("Mastermind")
		mm.addSnapshotListener { snapshots, e ->
			for (dc in snapshots!!.getDocumentChanges()) {
				docNom = dc.getDocument().getId()
				println(docNom)
				secMaster = dc.getDocument().getString("numSecret")!!
				println(secMaster)
				finalitzada = dc.getDocument().getBoolean("finalitzada")!!
				// Comprovació de si la partida està en marxa
				if (finalitzada) {
					println("Nova")
					novaPartida()
				} else {
					secret = secMaster
					println("Vella")
				}
				// Visualització de jugades
				mm.document(docNom).collection("tirades").orderBy("data").addSnapshotListener { snapshots, e ->
					for (dc in snapshots!!.getDocumentChanges()) {
						area.append(
							dc.getDocument().getString("nom") + ": " + dc.getDocument().getString("tirada") + " " +
									dc.getDocument().getString("colocades") + " " + dc.getDocument()
								.getString("desordenades") + "\n"
						)

						if (dc.getDocument().getString("colocades") == "4")
							finalPartida()
					}
				}
			}
		}


	}

	fun enviar() {
		val db = FirestoreClient.getFirestore()
		val mm = db.collection("Mastermind")

		val comp = comprova(tirada.getText(), secret)
		val dades = JugadaCF(jugador.getText(), tirada.getText(), comp[0].toString(), comp[1].toString(), Date())

		mm.document(docNom).collection("tirades").add(dades)

		tirada.setText("")
		tirada.requestFocus()
	}

	fun novaPartida() {
		area.setText("")
		val db = FirestoreClient.getFirestore()
		val mm = db.collection("Mastermind")

		// He d'assegurar-me que algun altre no l'ha ficada en marxa		
		val finalitza = mm.document(docNom).get().get().getBoolean("finalitzada")
		if (finalitza!!) {
			val dades = HashMap<String, Any>()
			dades.put("finalitzada", false)
			secret = genera()
			dades.put("numSecret", secret)

			mm.document(docNom).update(dades)

			// em falta esborrar els documents de la col·lecció tirades
		} else {
			secret = mm.document(docNom).get().get().getString("numSecret")!!
		}



		etIntroduccioJugada.setVisible(true)
		tirada.setVisible(true)
		enviar.setVisible(true)
		novaPartida.setVisible(false)

	}

	fun finalPartida() {
		area.append("---   Solució Trobada   ---\n")
		area.append("--- Partida Finalitzada ---")
		novaPartida.setVisible(true)
		etIntroduccioJugada.setVisible(false)
		enviar.setVisible(false)
		tirada.setVisible(false)

		val dades = HashMap<String, Any>()
		dades.put("finalitzada", true)

		val db = FirestoreClient.getFirestore()
		val mm = db.collection("Mastermind")
		mm.document(docNom).update(dades)

	}

	fun genera(): String {
		val i0 = (Math.random() * 10).toInt()
		var i1 = (Math.random() * 10).toInt()
		while (i1 == i0)
			i1 = (Math.random() * 10).toInt()
		var i2 = (Math.random() * 10).toInt()
		while (i2 == i0 || i2 == i1)
			i2 = (Math.random() * 10).toInt()
		var i3 = (Math.random() * 10).toInt()
		while (i3 == i0 || i3 == i1 || i3 == i2)
			i3 = (Math.random() * 10).toInt()
		return (i0.toString() + i1.toString() + i2.toString() + i3.toString())
	}

	fun comprova(num: String, sec: String): IntArray {
		var pos = 0
		var nopos = 0
		for (i in 0..3)
			for (j in 0..3)
				if (num[i] == sec[j])
					if (i == j) pos++
					else nopos++
		return intArrayOf(pos, nopos)
	}
}


fun main(args: Array<String>) {
	EventQueue.invokeLater {
		MastermindCF().isVisible = true
	}
}

