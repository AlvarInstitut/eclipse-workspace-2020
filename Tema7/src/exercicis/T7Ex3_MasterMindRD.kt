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
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ChildEventListener

class Jugada(var nom: String, var tirada: String, var colocades: String, var desordenades: String)

class MastermindRD : JFrame() {

	val etJugador = JLabel("Nom Jugador:")
	val jugador = JTextField(25)

	val etiqueta = JLabel("Jugades:")
	val area = JTextArea()

	val etIntroduccioJugada = JLabel("Introdueix jugada:")
	val enviar = JButton("Enviar")
	val tirada = JTextField(15)

	val novaPartida = JButton("Nova Partida")

	var secret = ""

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

		// Connexió a Realtime Database. Inicailització de la referència a Mastermind
		val serviceAccount = FileInputStream("xat-ad-firebase-adminsdk-my2d0-8c69944b34.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://xat-ad.firebaseio.com").build()

		FirebaseApp.initializeApp(options)

		val db = FirebaseDatabase.getInstance()
		val mm = db.getReference("Mastermind")
		val tirades = mm.child("tirades")
		val secMaster = mm.child("secret")
		val finalitzada = mm.child("finalitzada")

		// Comprovació de si la partida està en marxa
		mm.addListenerForSingleValueEvent(
			object : ValueEventListener {
				override
				fun onDataChange(dataSnapshot: DataSnapshot) {
					if (dataSnapshot.getValue() == null || dataSnapshot.child("finalitzada").getValue() as Boolean) {
						novaPartida()
					} else {
						secret = dataSnapshot.child("secret").getValue() as String
					}
				}

				override
				fun onCancelled(error: DatabaseError) {
				}
			}
		)

		// Visualització de jugades
		tirades.addChildEventListener(
			object : ChildEventListener {
				override
				fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
					area.append(
						dataSnapshot.child("nom").getValue().toString() + ": "
								+ dataSnapshot.child("tirada").getValue().toString() + " "
								+ dataSnapshot.child("colocades").getValue().toString() + " "
								+ dataSnapshot.child("desordenades").getValue().toString() + "\n"
					)
					if (dataSnapshot.child("colocades").getValue().toString() == "4")
						finalPartida()
				}

				override
				fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {
				}

				override
				fun onChildRemoved(dataSnapshot: DataSnapshot) {
				}

				override
				fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {
				}

				override
				fun onCancelled(databaseError: DatabaseError) {
				}
			}
		)

	}


	fun enviar() {
		val db = FirebaseDatabase.getInstance()
		val mm = db.getReference("Mastermind")
		val tirades = mm.child("tirades")
		val comp = comprova(tirada.getText(), secret)

		tirades.push()
			.setValue(Jugada(jugador.getText(), tirada.getText(), comp[0].toString(), comp[1].toString()), null)
		tirada.setText("")
		tirada.requestFocus()
	}

	fun novaPartida() {
		area.setText("")
		val db = FirebaseDatabase.getInstance()
		val mm = db.getReference("Mastermind")
		val finalitzada = mm.child("finalitzada")
		val tirades = mm.child("tirades")

		// He d'assegurar-me que algun altre no l'ha ficada en marxa
		mm.addListenerForSingleValueEvent(
			object : ValueEventListener {
				override
				fun onDataChange(dataSnapshot: DataSnapshot) {
					if (dataSnapshot.child("finalitzada").getValue() as Boolean) {
						finalitzada.setValue(false, null)
						secret = genera()
						mm.child("secret").setValue(secret, null)
						tirades.setValue(null, null)

					} else {
						secret = dataSnapshot.child("secret").getValue() as String
					}
				}

				override
				fun onCancelled(error: DatabaseError) {
				}
			}
		)

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
		val finalitzada = FirebaseDatabase.getInstance().getReference("Mastermind/finalitzada")
		finalitzada.setValue(true, null)
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
		MastermindRD().isVisible = true
	}
}

