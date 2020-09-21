package exercicis

import javax.swing.*
import java.awt.*
import java.io.File

class Exercici_2_2 : JFrame() {
	val et_f = JLabel("Fitxer:")
	val fitxer = JTextField(25)
	val obrir = JButton("Obrir")
	val guardar = JButton("Guardar")
	val et_a = JLabel("Contingut:")
	val area = JTextArea(10, 50)
	val scrollPane = JScrollPane(area)

	// en iniciar posem un contenidor per als elements anteriors
	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE

		setLayout(GridLayout(2, 1))
		setTitle("Editor de text")

		val panell1 = JPanel(GridLayout(0, 1))
		val panell1_1 = JPanel(FlowLayout())
		panell1.add(panell1_1)
		panell1_1.add(et_f)
		panell1_1.add(fitxer)

		val panell1_2 = JPanel(FlowLayout())
		panell1.add(panell1_2)
		panell1_2.add(obrir)
		panell1_2.add(guardar)
		val panell2 = JPanel(GridLayout(0, 1))
		panell2.add(scrollPane)
		area.setEditable(true)

		add(panell1)
		add(panell2)
		pack()

		obrir.addActionListener { obrir() }

		guardar.addActionListener { guardar() }
	}

	fun obrir() {
		// Instruccions per a bolcar el contingut del fitxer en el JTextArea
		if (File(fitxer.getText()).exists())
			area.setText(File(fitxer.getText()).readText())
		else
			println("No existeix el fitxer")
	}

	fun guardar() {
		// Instruccions per a guardar el contingut del JTextArea al fitxer.
		if (fitxer.getText() != "")
			File(fitxer.getText()).writeText(area.getText())
		else
			println("No pots deixar en blanc elnom del fitxer")
	}
}


fun main(args: Array<String>) {
	EventQueue.invokeLater( { Exercici_2_2().isVisible = true })
}

