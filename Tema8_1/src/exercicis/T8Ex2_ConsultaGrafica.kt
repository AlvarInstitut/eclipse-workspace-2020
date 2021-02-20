package exercicis

import javax.swing.JFrame
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JTextArea
import javax.swing.DefaultListModel
import javax.swing.JList
import javax.swing.JScrollPane
import java.awt.FlowLayout
import java.awt.Color
import redis.clients.jedis.Jedis

import java.awt.EventQueue

class EstadisticaRD : JFrame() {

	val etTipClau = JLabel("Tipus:")
	val tipClau = JTextField(8)
	val contClau = JTextArea(8, 15)
	val listModel = DefaultListModel<String>()
	val llClaus = JList(listModel)

	val con = Jedis("89.36.214.106")

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setBounds(100, 100, 450, 450)
		setLayout(FlowLayout())


		llClaus.setForeground(Color.blue)
		val scroll = JScrollPane(llClaus)
		llClaus.setVisibleRowCount(20)

		val scroll2 = JScrollPane(contClau)

		add(scroll)
		add(etTipClau)
		add(tipClau)
		add(scroll2)

		setSize(600, 400)
		setVisible(true)

		inicialitzar()

		llClaus.addListSelectionListener { valorCanviat() }

	}

	fun inicialitzar() {
		con.auth("ieselcaminas.ad")
		val claus = con.keys("*").sorted()
		for (c in claus)
			listModel.addElement(c)
	}

	fun valorCanviat() {
		contClau.setText("")
		val clau = llClaus.getSelectedValue()
		tipClau.setText(con.type(clau))
		when (con.type(clau).toString()) {
			"string" -> contClau.setText(con.get(clau))
			"hash"   -> for(camp in con.hkeys(clau))
							contClau.append(camp + " --> " + con.hget(clau,camp) + "\n")
			"list"   -> for(e in con.lrange(clau,0,-1))
							contClau.append(e + "\n")
			"set"   -> for(e in con.smembers(clau))
							contClau.append(e + "\n")
			"zset"   -> for(c in con.zrangeWithScores(clau,0,-1))
							contClau.append(c.getElement() + " --> " + c.getScore() + "\n")
			
		}
	}
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		EstadisticaRD().isVisible = true
	}
}