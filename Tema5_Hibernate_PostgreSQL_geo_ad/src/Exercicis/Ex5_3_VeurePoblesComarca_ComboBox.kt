package Exercicis

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.hibernate.Query;
import org.hibernate.Session;

import classes.SessionFactoryUtil;
import classes.Comarca;
import java.awt.EventQueue

class FinestraComboBox : JFrame() {
	val etIni = JLabel("Manteniment de COMARQUES")
	val etNom = JLabel("Nom comarca")
	val etProv = JLabel("Nom província")

	val nomComarca = JTextField()
	val nomProvincia = JTextField()

	val primer = JButton("<<")
	val anterior = JButton("<")
	val seguent = JButton(">")
	val ultim = JButton(">>")

	val pDalt = JPanel(FlowLayout())
	val pCentre = JPanel(GridLayout(8, 0))
	val pDades = JPanel(GridLayout(2,2))
    val pBotonsMov = JPanel(FlowLayout())
 
	val s = SessionFactoryUtil.sessionFactory.openSession();

	var llistaComarques = ArrayList<Comarca>()
	val indActual = 0;

	init {
		defaultCloseOperation = JFrame.EXIT_ON_CLOSE
		setTitle("HIBERNATE: Manteniment Comarques")
                
   		setBounds(100, 100, 350, 400)
		setLayout(BorderLayout())

		getContentPane().add(pCentre, BorderLayout.CENTER)
        getContentPane().add(JPanel(FlowLayout()), BorderLayout.WEST)
        getContentPane().add(JPanel(FlowLayout()), BorderLayout.EAST)

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

        pBotonsMov.add(primer);
        pBotonsMov.add(anterior);
        pBotonsMov.add(seguent);
        pBotonsMov.add(ultim);
        pCentre.add(pBotonsMov);
        
        pCentre.add(JPanel(FlowLayout()))

        llistaComarques = agafarComarques()
        visComarca(indActual)
        
        primer.addActionListener() { primer()}
        anterior.addActionListener() {anterior()}
        seguent.addActionListener() {seguent()}
        ultim.addActionListener() { ultim()}
	}

	fun agafarComarques(): ArrayList<Comarca> {
        var llista = ArrayList<Comarca>()
        // ací aniran les sentències per a omplir (i retornar) la llista de comarques
		
        return llista;
	}

	fun visComarca(index: Int) {
        // Mètode per a visualitzar la comarca marcada per l'índex que ve com a paràmetre
        // Es pot aprofitar per a activar/desactivar els botons anterior i següent, si s'està en la primera o última comarca
    }
	
	fun primer() {
		
	}
	
	fun anterior(){
		
	}

	fun seguent(){
		
	}
	
	fun ultim() {
		
	}

}

fun main() {
	EventQueue.invokeLater {
		FinestraComboBox().isVisible = true
	}
}

