package exercicis

import java.awt.EventQueue
import java.awt.GridLayout
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.BoxLayout
import javax.swing.JComboBox
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JTable
import javax.swing.JScrollPane

import utilBd.Ruta
import utilBd.PuntGeo
import com.db4o.Db4oEmbedded

class FinestraComplet : JFrame() {
	var llista = arrayListOf<Ruta>()
	var numActual = 0

	// Declaració de la Base de Dades
	val bd = Db4oEmbedded.openFile("Rutes.db4o")

	val qNom = JTextField(15)
	val qDesn = JTextField(5)
	val qDesnAcum = JTextField(5)
	val qDistancia = JTextField(5)
	val punts = JTable(1, 3)
	val primer = JButton(" << ")
	val anterior = JButton(" < ")
	val seguent = JButton(" > ")
	val ultim = JButton(" >> ")
	val tancar = JButton("Tancar")

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("JDBC: Visualitzar Rutes Complet")
		setLayout(GridLayout(0, 1))

		val p_prin = JPanel()
		p_prin.setLayout(BoxLayout(p_prin, BoxLayout.Y_AXIS))
		val panell1 = JPanel(GridLayout(0, 2))
		panell1.add(JLabel("Ruta:"))
		qNom.setEditable(false)
		panell1.add(qNom)
		panell1.add(JLabel("Desnivell:"))
		qDesn.setEditable(false)
		panell1.add(qDesn)
		panell1.add(JLabel("Desnivell acumulat:"))
		qDesnAcum.setEditable(false)
		panell1.add(qDesnAcum)
		panell1.add(JLabel("Distància:"))
		qDistancia.setEditable(false)
		panell1.add(qDistancia)
		panell1.add(JLabel("Punts:"))

		val panell2 = JPanel(GridLayout(0, 1))
		punts.setEnabled(false)
		val scroll = JScrollPane(punts)
		panell2.add(scroll, null)

		val panell5 = JPanel(FlowLayout())
		panell5.add(primer)
		panell5.add(anterior)
		panell5.add(seguent)
		panell5.add(ultim)

		val panell6 = JPanel(FlowLayout())
		panell6.add(tancar)

		add(p_prin)
		p_prin.add(panell1)
		p_prin.add(panell2)
		p_prin.add(panell5)
		p_prin.add(panell6)
		pack()

		primer.addActionListener {
			// instruccions per a situar-se en la primera ruta, i visualitzar-la
			numActual=0
			VisRuta()
		}
		anterior.addActionListener {
			// instruccions per a situar-se en la ruta anterior, i visualitzar-la
			numActual--
			VisRuta()
		}
		seguent.addActionListener {
			// instruccions per a situar-se en la ruta següent, i visualitzar-la
			numActual++
			VisRuta()
		}
		ultim.addActionListener {
			// instruccions per a situar-se en l'últim ruta, i visualitzar-la
			numActual=llista.size-1
			VisRuta()
		}
		tancar.addActionListener {
			// instruccions per a tancar la BD i el programa
			bd.close()
			System.exit(0)
		}

		inicialitzar()
		VisRuta()
	}

	fun plenarTaula(ll_punts: MutableList<PuntGeo>) {
		var ll = Array(ll_punts.size) { arrayOfNulls<String>(3) }
		for (i in 0 until ll_punts.size) {
			ll[i][0] = ll_punts.get(i).nom
			ll[i][1] = ll_punts.get(i).coord.latitud.toString()
			ll[i][2] = ll_punts.get(i).coord.longitud.toString()
		}
		val caps = arrayOf("Nom punt", "Latitud", "Longitud")
		punts.setModel(javax.swing.table.DefaultTableModel(ll, caps))
	}

	fun inicialitzar() {
		// instruccions per a inicialitzar llista i numActual
		val q = bd.queryByExample<Ruta>(Ruta(null,null,null))
		llista = ArrayList(q)
		numActual=0
	}

	fun VisRuta() {
		// instruccions per a visualitzar la ruta actual (l'índex el tenim en numActual
		val r = llista.get(numActual)
		qNom.setText(r.nom)
		qDesn.setText(r.desnivell.toString())
		qDesnAcum.setText(r.desnivellAcumulat.toString())
		var di = 0.0
		for (i in 0..r.llistaDePunts.size-2){
				di += Dist(r.getPuntLatitud(i),r.getPuntLongitud(i),r.getPuntLatitud(i+1),r.getPuntLongitud(i+1))
		}
		qDistancia.setText(di.toString())
		plenarTaula(llista.get(numActual).llistaDePunts)
		ActivarBotons()
	}

	fun ActivarBotons() {
		// instruccions per a activar o desactivar els botons de moviment ( setEnabled(Boolean) )
		anterior.setEnabled(numActual!=0)
		seguent.setEnabled(numActual!=(llista.size-1))

	}

}

fun Dist(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {

	val R = 6378.137 // Radi de la Tierra en km
	val dLat = rad(lat2 - lat1)
	val dLong = rad(lon2 - lon1)

	val a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(rad(lat1)) * Math.cos(rad(lat2)) * Math.sin(dLong / 2) * Math.sin(dLong / 2)
	val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
	val d = R * c
	return Math.round(d*100.0)/100.0
}

fun rad(x: Double): Double {
	return x * Math.PI / 180
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		FinestraComplet().isVisible = true
	}
}

