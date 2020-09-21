package exercicis

import javax.swing.*
import java.awt.*
import org.json.JSONTokener
import org.json.JSONObject
import org.json.JSONArray
import java.net.URL
import java.io.InputStreamReader

class FinestraBicicas : JFrame() {
	var estacions = JSONArray()
	var listModel = DefaultListModel<String>()
	var list = JList(listModel)
	var area = JTextArea(5, 15)

	init {

		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("JSON: Vista detallada BICICAS")
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

		panell1.add(JLabel("LListat actual BICICAS"))

		agafarBicicas()

		mostrarEstacions()

		list.addListSelectionListener() {
			if (list.getSelectedIndex() >= 0)
				visualitzaEstacio(list.getSelectedValue())
		}
	}

	fun agafarBicicas() {
		// Instruccions per a llegir de la pàgina de Bicicas i col·locar en arrel
		val bicicas = URL("http://gestiona.bicicas.es/apps/apps.php");
		val arrel = (JSONTokener(bicicas.openConnection().getInputStream()).nextValue() as JSONArray).get(0) as JSONObject
		// Instruccions per a col·locar les estacions en estacions (JsonArray)
		estacions = arrel.get("ocupacion") as JSONArray
	}

	fun mostrarEstacions() {
		// Instruccions per a introduir en el JList les estacions
		// La manera d'anar introduint informació en el JList és a través del DefaultListModel:
		// listModel.addElement("Linia que es vol introduir ")
		for (e in estacions) {
			e as JSONObject
			listModel.addElement("" + e.get("id") + ".- " + e.get("punto"))
		}
	}

	fun visualitzaEstacio(estacio: String) {
		// Instruccions per a mostrar les característiques en el area, el JTextArea de la dreta
		val e = estacions.get(list.getSelectedIndex()) as JSONObject
		area.setText("")
		area.append("\t" + e.get("id") + "  -  " + e.get("punto") + "\n\n")
		area.append("Número estació: " + e.get("id") + "\n")
		area.append("Nom estació: " + e.get("punto") + "\n")
		area.append("Posicions: " + e.get("puestos") + "\n")
		area.append("Posicions ocupades: " + e.get("ocupados") + "\n")
		area.append("Latitud: " + e.get("latitud") + "\n")
		area.append("Longitud: " + e.get("longitud") + "\n")
		area.append("Percentatge alta ocupació: " + e.get("porcentajeAltaOcupacion") + "\n")
		area.append("Percentatge baixa ocupació: " + e.get("porcentajeBajaOcupacion") + "\n")

	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		FinestraBicicas().isVisible = true
	}
}

