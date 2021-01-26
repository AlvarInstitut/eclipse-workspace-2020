package exercicis

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JComboBox
import javax.swing.JTextArea
import javax.swing.JButton
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.JPanel
import java.awt.Color
import javax.swing.JScrollPane
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.cloud.FirestoreClient
import java.awt.EventQueue
import com.google.firebase.cloud.StorageClient
import java.nio.file.Paths
import java.applet.Applet
import java.applet.AudioClip

class SonsCS_CF : JFrame() {

	val etAudios = JLabel("Llista de Audios:")
	val comboAudios = JComboBox<String>()

	val play = JButton("Play")
	val stop = JButton("Stop")

	var clip: AudioClip? = null

	// en iniciar posem un contenidor per als elements anteriors
	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setBounds(100, 100, 500, 150)
		setLayout(BorderLayout())
		// contenidor per als elements
		//Hi haurà títol. Panell de dalt: últim missatge. Panell de baix: per a introduir missatge. Panell central: tot el xat

		val panell1 = JPanel(FlowLayout())
		panell1.add(etAudios)
		panell1.add(comboAudios)
		getContentPane().add(panell1, BorderLayout.NORTH)

		val panell2 = JPanel(FlowLayout())
		panell2.add(play)
		panell2.add(stop)
		getContentPane().add(panell2, BorderLayout.SOUTH)
		setVisible(true)

		val serviceAccount = FileInputStream("xat-ad-firebase-adminsdk-my2d0-8c69944b34.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.build()

		FirebaseApp.initializeApp(options)

		val db = FirestoreClient.getFirestore()

		// Instruccions per a omplir el JComboBox amb els audios
		val documents = db.collection("Audios").get().get().getDocuments()
		val llistaAudios = ArrayList<String>()
		val llistaFitxers = ArrayList<String>()
		if (documents != null) {
			for (document in documents) {
				llistaAudios.add(document.getString("nom")!!)
				llistaFitxers.add(document.getString("fitxer")!!)
			}
		}
		for (p in llistaAudios)
			comboAudios.addItem(p)

		val bucket = StorageClient.getInstance().bucket("xat-ad.appspot.com")
		// Instruccions per fer sonar l'audio triat
		comboAudios.addActionListener() {
			println(llistaFitxers[comboAudios.getSelectedIndex()])
			val blob = bucket?.get(llistaFitxers[comboAudios.getSelectedIndex()])
			val destFilePath = Paths.get("auxiliar.jpg")
			blob?.downloadTo(destFilePath)

			clip = Applet.newAudioClip(destFilePath.toUri().toURL())
			//println(destFilePath.toFile().size())

		}

		play.addActionListener() {
			println("A sonar")
			clip?.loop()
		}

		stop.addActionListener() {
			clip?.stop()
		}


	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		SonsCS_CF().isVisible = true
	}
}