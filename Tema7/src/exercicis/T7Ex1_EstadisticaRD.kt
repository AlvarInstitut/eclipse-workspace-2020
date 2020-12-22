package exercicis

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JComboBox
import javax.swing.JTextArea
import java.awt.BorderLayout
import javax.swing.JPanel
import java.awt.FlowLayout
import java.awt.Color
import javax.swing.JScrollPane
import java.io.FileInputStream
import com.google.firebase.FirebaseOptions
import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ChildEventListener
import org.json.JSONArray
import org.json.JSONObject
import java.awt.EventQueue


class EstadisticaRD : JFrame() {

	val etProv = JLabel("Provincia: ")
	val provincia = JComboBox<String>()

	val etiqueta = JLabel("Missatges:")
	val area = JTextArea()


	// en iniciar posem un contenidor per als elements anteriors
	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setBounds(100, 100, 450, 450)
		setLayout(BorderLayout())
		// contenidor per als elements
		//Hi haurà títol. Panell de dalt: últim missatge. Panell de baix: per a introduir missatge. Panell central: tot el xat

		val panell1 = JPanel(FlowLayout())
		panell1.add(etProv)
		panell1.add(provincia)
		getContentPane().add(panell1, BorderLayout.NORTH)

		val panell2 = JPanel(BorderLayout())
		panell2.add(etiqueta, BorderLayout.NORTH)
		area.setForeground(Color.blue)
		area.setEditable(false)
		val scroll = JScrollPane(area)
		panell2.add(scroll, BorderLayout.CENTER)
		getContentPane().add(panell2, BorderLayout.CENTER)

		setVisible(true)

		val serviceAccount = FileInputStream("xat-ad-firebase-adminsdk-my2d0-8c69944b34.json")

		val options = FirebaseOptions.Builder()
			.setCredentials(GoogleCredentials.fromStream(serviceAccount))
			.setDatabaseUrl("https://xat-ad.firebaseio.com").build()

		FirebaseApp.initializeApp(options)

		// Exemple de listener d'una llista addChildEventListener()
		// Per a posar tota la llista de províncies al JComboBox
		val est = FirebaseDatabase.getInstance().getReference("EstadisticaVariacioPoblacional")

		est.addChildEventListener(
			object : ChildEventListener {
				override
				fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
					provincia.addItem(dataSnapshot.child("Nombre").getValue().toString())
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

		provincia.addActionListener() {
			// Exemple de listener de lectura única addListenerForSingleValue()
			// Per a posar el títol. Sobre nomXat
			area.setText("")
			val provin = est.child(provincia.getSelectedIndex().toString()).child("Data")
			provin.addChildEventListener(
				object : ChildEventListener {
					override
					fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
						area.append(dataSnapshot.child("NombrePeriodo").getValue().toString() + ": " +
									dataSnapshot.child("Valor").getValue().toString() + "\n"
						)
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
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		EstadisticaRD().isVisible = true
	}
}