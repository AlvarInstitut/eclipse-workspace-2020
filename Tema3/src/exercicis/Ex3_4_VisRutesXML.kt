package exercicis

import javax.swing.*
import java.awt.*
import org.w3c.dom.Document
import org.w3c.dom.Element
import javax.xml.parsers.DocumentBuilderFactory

class Finestra : JFrame() {
	
	init {
		var doc: Document
		// sentències per a omplir doc
		doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("Rutes.xml")
		
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("Punts d'una ruta")
		setSize(400, 300)
		setLayout(BorderLayout())
		
		val panell1 = JPanel(FlowLayout())
		val panell2 = JPanel(BorderLayout())
		add(panell1,BorderLayout.NORTH)
		add(panell2,BorderLayout.CENTER)
		
		val llistaRutes = arrayListOf<String>()
		// sentències per a omplir l'ArrayList anterior amb el nom de les rutes
		val llRutes = doc.getElementsByTagName("ruta")
		for (i in 0 until llRutes.getLength()){
			val r = llRutes.item(i) as Element
			llistaRutes.add(r.getElementsByTagName("nom").item(0).getTextContent())
		}
		
		val combo = JComboBox(llistaRutes.toArray())
		panell1.add(combo)
		
		panell2.add(JLabel("Llista de punts de la ruta:"),BorderLayout.NORTH)
		val area = JTextArea()
		panell2.add(area)
		
		combo.addActionListener{
			// accions quan s'ha seleccionat un element del combobox,
			// i que han de consistir en omplir el JTextArea
			area.setText("")
			val r = doc.getElementsByTagName("ruta").item(combo.getSelectedIndex()) as Element
			val punts = r.getElementsByTagName("punt")
			for (i in 0 until punts.getLength()){
				val p = punts.item(i) as Element
				area.append(p.getElementsByTagName("nom").item(0).getTextContent())
				area.append(" (" + p.getElementsByTagName("latitud").item(0).getTextContent())
				area.append("," + p.getElementsByTagName("longitud").item(0).getTextContent() + ")\n")
			}
			
		}
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		Finestra().isVisible = true
	}
}

