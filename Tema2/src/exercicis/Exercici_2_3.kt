package exercicis

import javax.swing.*
import java.awt.*
import java.io.File

class Exercici_2_3 : JFrame() {
	val area = JTextArea()
	val scrollPane = JScrollPane(area)

	val menu_p = JMenuBar()

	val menu_arxiu = JMenu("Arxiu")
	val menu_ajuda = JMenu("Ajuda")

	val obrir = JMenuItem("Obrir")
	val guardar = JMenuItem("Guardar")
	val guardarCom = JMenuItem("Guardar com ...")
	val eixir = JMenuItem("Eixir")

	val quantA = JMenuItem("Quant a Editor")

	val fCh = JFileChooser()

	// en iniciar posem un contenidor per als elements anteriors
	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE

		setLayout(BorderLayout())
		setTitle("Editor de text més avançat")
		add(scrollPane)
		area.setEditable(true)

		setSize(750, 400)
		setJMenuBar(menu_p)

		menu_p.add(menu_arxiu)
		menu_p.add(menu_ajuda)

		menu_arxiu.add(obrir)
		menu_arxiu.add(guardar)
		menu_arxiu.add(guardarCom)
		menu_arxiu.add(JSeparator())
		menu_arxiu.add(eixir)

		menu_ajuda.add(quantA);

		obrir.addActionListener { obrir() }

		guardar.addActionListener { guardar() }

		guardarCom.addActionListener { guardarCom() }

		eixir.addActionListener { eixir() }

		quantA.addActionListener { quantA() }
	}

	fun obrir() {
		// Instruccions per a obrir un fitxer i posar el contingut en el JTextArea
		val r = fCh.showOpenDialog(this)
		if (r == JFileChooser.APPROVE_OPTION) {
			area.setText(fCh.getSelectedFile().readText())
		}
	}

	fun guardar() {
		// Instruccions per a guardar el contingut del JTextArea al fitxer.
		if (fCh.getSelectedFile() != null)
			fCh.getSelectedFile().writeText(area.getText())
		else
			guardarCom()
	}

	fun guardarCom() {
		// Instruccions per a guardar el contingut del JTextArea al fitxer, amb la possibilitat de canviar el nom
		val r = fCh.showSaveDialog(this)
		if (r == JFileChooser.APPROVE_OPTION) {
			fCh.getSelectedFile().writeText(area.getText())
		}

	}

	fun eixir() {
		// Instruccions per a eixir
		System.exit(0)
	}

	fun quantA() {
		// Instruccions per a mostrar un diàleg amb la versió (Acerca de...)
		JOptionPane.showMessageDialog(this, "Editor 1.0")
	}
}


fun main(args: Array<String>) {
	EventQueue.invokeLater({ Exercici_2_3().isVisible = true })
}


