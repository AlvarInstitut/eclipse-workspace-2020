package exemples

import javax.swing.*
import java.awt.*

class Finestra_dialeg(f: Finestra) : JDialog(f, "Diàleg", true) {
	init {
		setSize(200, 100)
		setLocationRelativeTo(null)
		val panell = JPanel()
		panell.add(JLabel("Hola, què tal?"))
		add(panell)
		setVisible(true)
	}
}

class Finestra : JFrame() {
	val boto1 = JButton("Botó 1")
	val fc = JFileChooser()

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("Exemple de JDialog")
		setSize(400, 300)
		setLocationRelativeTo(null)
		setLayout(GridLayout(2, 1))
		val panell1 = JPanel(FlowLayout())
		val panell2 = JPanel(FlowLayout())
		add(panell1)
		add(panell2)

		panell1.add(JLabel("Programa principal. Per anar al diàleg apreta el botó"))
		panell2.add(boto1)

		boto1.addActionListener {
			val r = fc.showOpenDialog(this)
			if (r == JFileChooser.APPROVE_OPTION) {
				println("Fitxer seleccionat: " + fc.getSelectedFile().getName());
			} else
				println("No s'ha seleccionat res");
		}
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		Finestra().isVisible = true
	}
}