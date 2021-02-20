package Exercicis

import java.awt.EventQueue
import java.awt.BorderLayout
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.JTextArea
import javax.swing.JLabel
import javax.swing.JTextField
import java.awt.Color
import javax.swing.JScrollPane
import classes.SessionFactoryUtil
import classes.Comarca
import classes.Poblacio

class Finestra : JFrame() {
	val etiqueta = JLabel("Comarca:")
	val etIni = JLabel("Introdueix la comarca:")
	val com = JTextField(15)
	val consultar = JButton("Consultar")
	val area = JTextArea()

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("HIBERNATE: Visualitzar Comarques i Pobles")
		setBounds(100, 100, 450, 300)
		setLayout(BorderLayout())

		val panell1 = JPanel(FlowLayout())
		panell1.add(etIni)
		panell1.add(com)
		panell1.add(consultar)
		getContentPane().add(panell1, BorderLayout.NORTH)

		val panell2 = JPanel(BorderLayout())
		panell2.add(etiqueta, BorderLayout.NORTH)
		area.setForeground(Color.blue)
		val scroll = JScrollPane(area)
		panell2.add(scroll, BorderLayout.CENTER)
		getContentPane().add(panell2, BorderLayout.CENTER)

		consultar.addActionListener() {
			etiqueta.setText("Comarca: " + com.getText())
			visualitzaCom(com.getText())
		}
	}

	fun visualitzaCom(comarca: String) {
		// Instruccions per a llegir la comarca que arriba com a paràmetre (s'ha de deixar en un objecte Comarques).
		// S'ha de cuidar que si no exiteix la comarca, en el JTextArea es pose que no existeix.
		// La manera d'anar introduint informació en el JTextArea és area.append("Linia que es vol introduir ")
		
		val sessio = SessionFactoryUtil.sessionFactory.openSession()
		val c = sessio.get("classes.Comarca",comarca) as Comarca?
		
		if (c == null)
			area.setText("No existeix la comarca " + comarca)
		else {
			area.setText("La comarca " + comarca + " té " + c.poblacios?.size + " pobles\n\n")
			c.poblacios!!.forEach {
				area.append((it as Poblacio).nom + "\n")
			}
		}
		sessio.close()
	}
}

fun main() {
	EventQueue.invokeLater {
		Finestra().isVisible = true
	}
}

