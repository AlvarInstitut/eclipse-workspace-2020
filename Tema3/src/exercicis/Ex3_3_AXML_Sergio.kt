package exercicis

import java.io.FileInputStream
import java.io.ObjectInputStream
import javax.xml.parsers.DocumentBuilderFactory
import java.io.EOFException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.OutputKeys
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

fun main(args: Array<String>) {
	
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))
	val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
	val arrel = doc.createElement("rutes")
	doc.appendChild(arrel)

	try {
		while (true) {
			
			// RUTA
			val e = f.readObject() as Ruta
			val ruta = doc.createElement("ruta")  // ruta es hijo de rutes
			

			// NOMBRE
			val name = doc.createElement("nom")
			name.appendChild(doc.createTextNode(e.nom)) // forma llarga: afegim un fill que és un node de text
			ruta.appendChild(name)

			// DESNIVELL
			val ramp = doc.createElement("desnivell")
			ramp.setTextContent(e.desnivell.toString()) // forma curta: amb setTextContent() li posem contingut
			ruta.appendChild(ramp)

			// DESNIVELL ACUMULAT
			val acc_drop = doc.createElement("desnivellAcumulat")
			acc_drop.setTextContent(e.desnivellAcumulat.toString())
			ruta.appendChild(acc_drop)

			val points = doc.createElement("punts");
			//sou.setTextContent(e.sou.toString())
			//ruta.appendChild(point)

			val point = doc.createElement("punt")
			
			ruta.appendChild(points)  // punts hijo de ruta
			//point.appendChild(points)  // puntos hijo de punto
			arrel.appendChild(ruta)  // ruta hijo del arrel (rutes)  
		}

	} catch (eof: EOFException) {
		f.close();
	}
	val trans = TransformerFactory.newInstance().newTransformer()
	//trans.setOutputProperty(OutputKeys.INDENT, "yes")//////////////////////////////// NO ESENCIAL
	//trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2")//////// NO ESENCIAL
	trans.transform(DOMSource(doc), StreamResult("Rutes_Sergio.xml"))
}