package exemples

import javax.swing.JFrame
import java.awt.EventQueue
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.GridLayout
import java.awt.Image
import java.io.BufferedReader
import java.io.IOException
import java.lang.reflect.InvocationTargetException
import java.sql.Connection
import java.sql.DriverManager
import java.sql.ResultSet
import java.sql.SQLException

import javax.imageio.ImageIO
import javax.swing.ImageIcon
import javax.swing.JCheckBox
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextArea
import javax.swing.JTextField
import java.io.Serializable

class Finestra : JFrame() {
	val nif = JTextField(9)
    val nom = JTextField(9)
    val major = JCheckBox()
    var foto: JLabel? = null
    val curric = JTextArea()
    val adreca = JTextField(20)
    val correus = JTextArea()
    val telefons = JTextArea()
    
    val et_nif = JLabel("Nif")
    val et_nom = JLabel("Nom")
    val et_major = JLabel("Major d'edat")
    val et_adr = JLabel("Adreça")
    val et_correus = JLabel("Correus")
    val et_telefons = JLabel("Telèfons")

    val pan1 = JPanel(GridLayout(1,2))
    val pan1_1 = JPanel(GridLayout(3,1))
    val pan1_1_1 = JPanel(FlowLayout())
    val pan1_1_2 = JPanel(FlowLayout())
    val pan1_1_3 = JPanel(FlowLayout())
    val pan1_2 = JPanel(FlowLayout())
    val pan2 = JPanel(BorderLayout())
    val pan2_1 = JPanel(FlowLayout())
    val pan2_2 = JPanel(GridLayout(2,2))
    val pan3 = JPanel(BorderLayout())
    val pan4 = JPanel()
	
	init{
	        setLayout(GridLayout(3,1))
        this.setBounds(100, 100, 300, 300)
        
        this.getContentPane().add(pan1)
        this.getContentPane().add(pan2)
        this.getContentPane().add(pan3)
        
        pan1.add(pan1_1)
        pan1.add(pan1_2)
        pan1_1.add(pan1_1_1)
        pan1_1.add(pan1_1_2)
        pan1_1.add(pan1_1_3)
        pan1_1_1.add(et_nif)
        pan1_1_1.add(nif)
        pan1_1_2.add(et_nom)
        pan1_1_2.add(nom)
        pan1_1_3.add(et_major)
        pan1_1_3.add(major)
        
        pan2.add(pan2_1,BorderLayout.NORTH)
        pan2.add(pan2_2,BorderLayout.CENTER)
        pan2_1.add(et_adr)
        pan2_1.add(adreca)
        pan2_2.add(et_correus)
        pan2_2.add(et_telefons)
        pan2_2.add(correus)
        pan2_2.add(telefons)
        
        pan3.add(curric)
        
        val url = "jdbc:postgresql://89.36.214.106:5432/r00"

        val con = DriverManager.getConnection(url, "r00", "r00")

        val rs = con.createStatement().executeQuery("SELECT * FROM persona4 WHERE nom='Eva'")
        
        if (rs.next()){
            nif.setText(rs.getString(1))
            nom.setText(rs.getString(2))
            major.setSelected(rs.getBoolean(3))
            if (rs.getBinaryStream(4)!=null){
                val img = ImageIO.read(rs.getBinaryStream(4))
                foto = JLabel(ImageIcon(img)) 
                pan1_2.add(foto)
            }
            if (rs.getCharacterStream(5)!=null){
                val bf = BufferedReader(rs.getCharacterStream(5))
                curric.setText(bf.readText())
            }
            if (rs.getObject(6)!=null){
                adreca.setText(rs.getObject(6).toString())

            }
                
            if (rs.getArray(7)!=null){
                val corr = rs.getArray(7).getArray() as Array<String>
                for (c in corr)
                correus.append(c+"\n")
            }
            if (rs.getArray(8)!=null){
                val tels = rs.getArray(8).getResultSet()
                while (tels.next())
                    telefons.append(tels.getString(2)+"\n")
            }    
        }
        
        rs.close()
        con.close()
	}
	
}

fun main(args: Array<String>) {
	EventQueue.invokeLater {
		Finestra().isVisible = true
	}
}
