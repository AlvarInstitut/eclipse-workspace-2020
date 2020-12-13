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
import javax.swing.table.DefaultTableModel

import utilBd.Ruta
import utilBd.PuntGeo
import utilBd.Coordenades
import com.db4o.Db4oEmbedded
import com.db4o.EmbeddedObjectContainer

class FinestraAvancat : JFrame() {
	var llista = arrayListOf<Ruta>()
	var numActual = 0

	// Declaració de la Base de Dades amb lateinit, per a inicialitzar després
	lateinit var bd: EmbeddedObjectContainer

	var actualitzant = false
	var modificacio = ""

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

	val editar = JButton("Editar")
	val eliminar = JButton("Eliminar")
	val nova = JButton("Nova Ruta")

	val acceptar = JButton("Acceptar")
	val cancelar = JButton("Cancel·lar")
	val mesP = JButton("+p")
	val menysP = JButton("-p")

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("JDBC: Visualitzar Rutes Complet")
		setLayout(GridLayout(0, 1))

		// Inicialització de la BD, amb opcions de modificar i esborrar en cascada
		val conf = Db4oEmbedded.newConfiguration()
		conf.common().objectClass("utilBd.Ruta").cascadeOnUpdate(true)
		conf.common().objectClass("utilBd.PuntGeo").cascadeOnUpdate(true)
		conf.common().objectClass("utilBd.Ruta").cascadeOnDelete(true)
		conf.common().objectClass("utilBd.PuntGeo").cascadeOnDelete(true)
		bd = Db4oEmbedded.openFile(conf, "Rutes.db4o")


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
		panell5.add(editar)
		panell5.add(eliminar)
		panell5.add(nova)

		acceptar.setVisible(false)
		panell5.add(acceptar)
		cancelar.setVisible(false)
		panell5.add(cancelar)
		mesP.setVisible(false)
		panell5.add(mesP)
		menysP.setVisible(false)
		panell5.add(menysP)

		val panell6 = JPanel(FlowLayout())
		panell6.add(tancar)

		add(p_prin)
		p_prin.add(panell1)
		p_prin.add(panell2)
		p_prin.add(panell5)
		p_prin.add(panell6)
		ActivarAltres(true)
		pack()
		ActivarAltres(false)

		primer.addActionListener {
			// instruccions per a situar-se en la primera ruta, i visualitzar-la
			numActual = 0
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
			numActual = llista.size - 1
			VisRuta()
		}
		tancar.addActionListener {
			bd.close()
			System.exit(0)
		}

		editar.addActionListener {
			// instruccions per a editar la ruta que s'està veient en aquest moment
			// s'han d'activar els quadres de text, i el JTable
			ActivarAltres(true)
			ActivarQuadres(true)
			actualitzant = true
			modificacio = "editar"
		}

		eliminar.addActionListener {
			// instruccions per a eliminar la ruta que s'està veient en aquest moment
			ActivarAltres(true)
			mesP.setVisible(false)
			menysP.setVisible(false)
			actualitzant = true
			modificacio = "esborrar"

		}

		nova.addActionListener {
			// instruccions per a posar en blanc els quadres de texti el JTable, per a inserir una nova ruta
			// s'han d'activar els quadres de text, i el JTable			
			ActivarAltres(true)
			ActivarQuadres(true)
			PosarQuadresBlanc()
			actualitzant = true
			modificacio = "inserir"
		}

		acceptar.addActionListener {
			// instruccions per a acceptar l'acció que s'està fent (nova ruta, edició o eliminació)
			if (actualitzant) {
				if (punts.isEditing())
					punts.getCellEditor().stopCellEditing()

				when (modificacio) {
					"editar" -> {
						val r = llista[numActual]
						AssignaRuta(r)
						bd.store(r)
						llista = agafarRutes()
					}
					"esborrar" -> {
						bd.delete(llista.get(numActual))
						llista = agafarRutes()
						if (numActual == llista.size)
							numActual--
					}
					"inserir" -> {
						val nomR = qNom.getText()
						val r = Ruta(null, null, null)
						AssignaRuta(r)
						bd.store(r)
						llista = agafarRutes()
						var i = 0
						while (nomR != llista.get(i).nom)
							i++
						numActual = i
					}

				}
				ActivarAltres(false)
				ActivarQuadres(false)
				VisRuta()
				actualitzant = false
			}

		}

		cancelar.addActionListener {
			// instruccions per a cancel·lar l'acció que s'estava fent
			ActivarAltres(false)
			ActivarQuadres(false)
			VisRuta()
			actualitzant = false
		}

		mesP.addActionListener {
			// instruccions per a afegir una línia en el JTable
			val m = punts.getModel() as DefaultTableModel
			if (punts.getSelectedRow() == -1)
				m.addRow(arrayOfNulls<String>(3))
			else
				m.insertRow(punts.getSelectedRow(), arrayOfNulls<String>(3))
		}

		menysP.addActionListener {
			// instruccions per a llevar una línia del JTable
			val m = punts.getModel() as DefaultTableModel
			if (punts.getSelectedRow() != -1)
				m.removeRow(punts.getSelectedRow())
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
		// instruccions per a inicialitzar llistat i numActual
		llista = agafarRutes()
		numActual = 0
	}

	fun VisRuta() {
		// instruccions per a visualitzar la ruta actual (l'índex el tenim en numActual)
		val rutaActual = llista.get(numActual)
		qNom.setText(rutaActual.nom)
		qDesn.setText(rutaActual.desnivell.toString())
		qDesnAcum.setText(rutaActual.desnivellAcumulat.toString())
		var d = 0.0
		for (i in 0 until rutaActual.llistaDePunts.size - 1)
			d += Dist(rutaActual.llistaDePunts[i].coord.latitud,rutaActual.llistaDePunts[i].coord.longitud,
						rutaActual.llistaDePunts[i+1].coord.latitud,rutaActual.llistaDePunts[i+1].coord.longitud)
		qDistancia.setText(d.toString())
		plenarTaula(rutaActual.llistaDePunts)
		ActivarBotons()
	}

	fun ActivarBotons() {
		// instruccions per a activar o desactivar els botons de moviment ( setEnabled(Boolean) )
		if (numActual == 0)
			anterior.setEnabled(false)
		else
			anterior.setEnabled(true)
		if (numActual == llista.size - 1)
			seguent.setEnabled(false)
		else
			seguent.setEnabled(true)
	}

	fun ActivarAltres(b: Boolean) {
		// instruccions per a mostrar els botons acceptar, cancelar, mesP, menysP,
		// ocultar editar, eliminar, nova. O al revés
		// I descativar els de moviment
		acceptar.setVisible(b)
		cancelar.setVisible(b)
		mesP.setVisible(b)
		menysP.setVisible(b)

		editar.setVisible(!b)
		eliminar.setVisible(!b)
		nova.setVisible(!b)

		primer.setEnabled(!b)
		anterior.setEnabled(!b)
		seguent.setEnabled(!b)
		ultim.setEnabled(!b)

	}

	fun ActivarQuadres(b: Boolean) {
		// instruccions per activar (o desactivar) els quadres de text i el JTable
		qNom.setEditable(b)
		qDesn.setEditable(b)
		qDesnAcum.setEditable(b)
		punts.setEnabled(b)
	}

	fun PosarQuadresBlanc() {
		//instruccions per a posar en blanc els quadres de text i el JTable quan anem a una nova ruta
		qNom.setText("")
		qDesn.setText("")
		qDesnAcum.setText("")
		qDistancia.setText("")
		var ll = Array(0) { arrayOfNulls<String>(3) }
		val caps = arrayOf("Nom punt", "Latitud", "Longitud")
		punts.setModel(javax.swing.table.DefaultTableModel(ll, caps))
	}

	fun AssignaRuta(r: Ruta) {
		// instruccions per a guardar en el paràmetre r el valor dels quadres de text i JTable
		r.nom = qNom.getText()
		r.desnivell = qDesn.getText().toInt()
		r.desnivellAcumulat = qDesnAcum.getText().toInt()
		val m = Math.min(r.llistaDePunts.size, punts.getRowCount())
		for (i in 0 until m) {
			r.llistaDePunts[i].nom = punts.getValueAt(i, 0).toString()
			r.llistaDePunts[i].coord.latitud = punts.getValueAt(i, 1).toString().toDouble()
			r.llistaDePunts[i].coord.longitud = punts.getValueAt(i, 2).toString().toDouble()
		}
		if (r.llistaDePunts.size < punts.getRowCount())
			for (i in r.llistaDePunts.size until punts.getRowCount()) {
				r.addPunt(
					PuntGeo(
						punts.getValueAt(i, 0).toString(),
						Coordenades(
							punts.getValueAt(i, 1).toString().toDouble(),
							punts.getValueAt(i, 2).toString().toDouble()
						)
					)
				)
			}
		if (r.llistaDePunts.size > punts.getRowCount())
			for (i in (r.llistaDePunts.size - 1) downTo punts.getRowCount()) {
				bd.delete(r.llistaDePunts[i])
				r.llistaDePunts.removeAt(i)
				println("Esborrem el " + i)
			}
	}

	fun agafarRutes(): ArrayList<Ruta> {
		// instruccions per a tornar totes les rutes de la Base de Dades
		return ArrayList(bd.queryByExample<Ruta>(Ruta(null, null, null)))
	}

	fun Dist(lat1: Double, lon1: Double, lat2: Double, lon2: Double): Double {
		val R = 6378.137 // Radi de la Tierra en km
		val dLat = rad(lat2 - lat1)
		val dLong = rad(lon2 - lon1)

		val a =
			Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(rad(lat1)) * Math.cos(rad(lat2)) * Math.sin(dLong / 2) * Math.sin(
				dLong / 2
			)
		val c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a))
		val d = R * c
		return Math.round(d * 100.0) / 100.0
	}

	fun rad(x: Double): Double {
		return x * Math.PI / 180
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		FinestraAvancat().isVisible = true
	}
}