package Exercicis

import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.awt.EventQueue

import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

import org.hibernate.Query
import org.hibernate.Session

import classes.SessionFactoryUtil
import classes.Comarca
import java.awt.event.WindowEvent

import kotlin.system.exitProcess

class FinestraMantenimentComarquesAvancat : JFrame() {
	val etIni = JLabel("Manteniment de COMARQUES")
	val etNom = JLabel("Nom comarca")
	val etProv = JLabel("Nom província")

	val nomComarca = JTextField()
	val nomProvincia = JTextField()

	val primer = JButton("<<")
	val anterior = JButton("<")
	val seguent = JButton(">")
	val ultim = JButton(">>")

	val inserir = JButton("Inserir")
	val modificar = JButton("Modificar")
	val esborrar = JButton("Esborrar")

	val acceptar = JButton("Acceptar")
	val cancelar = JButton("Cancel·lar")

	val eixir = JButton("Eixir")

	val pDalt = JPanel(FlowLayout())
	val pCentre = JPanel(GridLayout(8, 0))
	val pDades = JPanel(GridLayout(2, 2))
	val pBotonsMov = JPanel(FlowLayout())
	val pBotonsAct = JPanel(FlowLayout())
	val pBotonsAccCanc = JPanel(FlowLayout())
	val pEixir = JPanel(FlowLayout())

	val s = SessionFactoryUtil.sessionFactory.openSession();

	var llistaComarques = ArrayList<Comarca>()
	var indActual = 0

	var accio: String = ""

	init {
		defaultCloseOperation = JFrame.DO_NOTHING_ON_CLOSE
		setTitle("HIBERNATE: Manteniment Comarques")

		setBounds(100, 100, 350, 400)
		setLayout(BorderLayout())

		getContentPane().add(pCentre, BorderLayout.CENTER)
		getContentPane().add(JPanel(FlowLayout()), BorderLayout.WEST)
		getContentPane().add(JPanel(FlowLayout()), BorderLayout.EAST)
		getContentPane().add(pEixir, BorderLayout.SOUTH)

		pDalt.add(etIni)
		pCentre.add(pDalt)

		pDades.add(etNom)
		pDades.add(nomComarca)
		pDades.add(etProv)
		pDades.add(nomProvincia)
		pCentre.add(pDades)

		nomComarca.setEditable(false)
		nomProvincia.setEditable(false)

		pCentre.add(JPanel(FlowLayout()))

		pBotonsMov.add(primer)
		pBotonsMov.add(anterior)
		pBotonsMov.add(seguent)
		pBotonsMov.add(ultim)
		pCentre.add(pBotonsMov)

		pBotonsAct.add(inserir)
		pBotonsAct.add(modificar)
		pBotonsAct.add(esborrar)
		pCentre.add(pBotonsAct)

		pBotonsAccCanc.add(acceptar)
		pBotonsAccCanc.add(cancelar)
		pCentre.add(pBotonsAccCanc)
		pBotonsAccCanc.setVisible(false)

		pEixir.add(eixir)

		llistaComarques = agafarComarques()
		visComarca()

		primer.addActionListener() { primer() }
		anterior.addActionListener() { anterior() }
		seguent.addActionListener() { seguent() }
		ultim.addActionListener() { ultim() }

		inserir.addActionListener() { inserir() }
		modificar.addActionListener() { modificar() }
		esborrar.addActionListener() { esborrar() }

		acceptar.addActionListener() { acceptar() }
		cancelar.addActionListener() { cancelar() }

		eixir.addActionListener() { eixir() }
	}

	fun agafarComarques(): ArrayList<Comarca> {
		var llista = ArrayList<Comarca>()
		// ací aniran les sentències per a omplir (i retornar) la llista de comarques
		llista = s.createQuery("from Comarca order by nomC").list() as ArrayList<Comarca>
		return llista
	}

	fun visComarca() {
		// Mètode per a visualitzar la comarca marcada per l'índex que ve com a paràmetre
		nomComarca.setText(llistaComarques.get(indActual).nomC)
		nomProvincia.setText(llistaComarques.get(indActual).provincia)
		controlBotons()
	}

	fun primer() {
		indActual = 0
		visComarca()
	}

	fun anterior() {
		indActual--
		visComarca()
	}

	fun seguent() {
		indActual++
		visComarca()
	}

	fun ultim() {
		indActual = llistaComarques.size - 1
		visComarca()
	}

	fun controlBotons() {
		// Mètode per a habilitar/deshabilitar els botons anterior i següent, si s'està en la primera o última comarca
		// No us oblideu d'habilitar-los quan toque
		anterior.setEnabled(indActual != 0)
		seguent.setEnabled(indActual != (llistaComarques.size - 1))
	}

	fun inserir() {
		//accions per a preparar per a inserir una nova comarca
		activarBotons(false)
		nomComarca.setEditable(true)
		nomProvincia.setEditable(true)
		nomComarca.setText("")
		nomProvincia.setText("")
		accio = "inserir"
	}

	fun modificar() {
		//accions per a preparar per a modificar la comarca actual
		activarBotons(false)
		nomProvincia.setEditable(true)
		accio = "modificar"
	}

	fun esborrar() {
		//accions per a preparar per a esborrar la comarca actual
		activarBotons(false)
		accio = "esborrar"

	}

	fun acceptar() {
		//accions per a fer l'acció d'inserir, modificar i esborrar
		when (accio) {
			"inserir" -> {
				val com = Comarca()
				com.nomC = nomComarca.getText()
				com.provincia = nomProvincia.getText()
				val t = s.beginTransaction()
				s.save(com)
				t.commit()

				llistaComarques = agafarComarques()
				indActual = buscaCom(nomComarca.getText())
			}
			"modificar" -> {
				llistaComarques.get(indActual).nomC = nomComarca.getText()
				llistaComarques.get(indActual).provincia = nomProvincia.getText()
				val t = s.beginTransaction()
				s.save(llistaComarques.get(indActual))
				t.commit()
			}
			"esborrar" -> {
				val t = s.beginTransaction()
				s.delete(llistaComarques.get(indActual))
				t.commit()
				llistaComarques = agafarComarques()
				if (indActual == llistaComarques.size)
					indActual--
			}
		}
		nomComarca.setEditable(false)
		nomProvincia.setEditable(false)
		activarBotons(true)
		visComarca()

	}

	fun cancelar() {
		//accions per a cancel·lar la inserció, modificació o esborrat
		nomComarca.setEditable(false)
		nomProvincia.setEditable(false)
		activarBotons(true)
		visComarca()
	}

	fun buscaCom(text: String): Int {
		// Busca la comarca passada com a paràmetre en llistaComarques, tornant el seu índex. Per a situar-se després d'una inserció.
		var ind = 0
		// Ací haurien d'anar les sentències
		for (i in 0 until llistaComarques.size)
			if (llistaComarques.get(i).nomC == text){
				ind = i
				break
			}
		return ind
	}

	fun activarBotons(b: Boolean) {
		// Mètode per activar o desactivar (segons el paràmetre) els botons de moviment i els d'actualització
		// Farem invisible o visible el panell dels botons acceptar i cancel·lar (pBotonsAccCanc
		primer.setEnabled(b)
		anterior.setEnabled(b)
		seguent.setEnabled(b)
		ultim.setEnabled(b)
		inserir.setEnabled(b)
		modificar.setEnabled(b)
		esborrar.setEnabled(b)
		pBotonsAccCanc.setVisible(!b)
	}

	fun eixir() {
		//accions per a tancar i per a eixir
		s.close()
		exitProcess(0)
	}
}

fun main() {
	EventQueue.invokeLater {
		FinestraMantenimentComarquesAvancat().isVisible = true
	}
}