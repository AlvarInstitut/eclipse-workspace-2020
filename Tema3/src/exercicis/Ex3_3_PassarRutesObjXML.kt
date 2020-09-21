package exercicis

import java.io.ObjectInputStream
import java.io.FileInputStream
import java.io.IOException
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult
import javax.xml.transform.OutputKeys

fun main(args: Array<String>) {
	val f = ObjectInputStream(FileInputStream("Rutes.obj"))

	val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument()
	val arrel = doc.createElement("rutes")
	doc.appendChild(arrel)
	try {
		while (true) {
			val r = f.readObject() as Ruta
			val ruta = doc.createElement("ruta")
			arrel.appendChild(ruta)

			val nom = doc.createElement("nom")
			nom.appendChild(doc.createTextNode(r.nom))
			ruta.appendChild(nom)

			val desn = doc.createElement("desnivell")
			desn.appendChild(doc.createTextNode(r.desnivell.toString()))
			ruta.appendChild(desn)

			val desnAc = doc.createElement("desnivellAcumulat")
			desnAc.appendChild(doc.createTextNode(r.desnivellAcumulat.toString()))
			ruta.appendChild(desnAc)

			val punts = doc.createElement("punts")
			ruta.appendChild(punts)

			var i = 1
			for (p in r.llistaDePunts) {
				val punt = doc.createElement("punt")
				punt.setAttribute("num", (i++).toString())
				punts.appendChild(punt)

				val nomP = doc.createElement("nom")
				nomP.appendChild(doc.createTextNode(p.nom))
				punt.appendChild(nomP)

				val lat = doc.createElement("latitud")
				lat.appendChild(doc.createTextNode(p.coord.latitud.toString()))
				punt.appendChild(lat)

				val lon = doc.createElement("longitud")
				lon.appendChild(doc.createTextNode(p.coord.longitud.toString()))
				punt.appendChild(lon)

			}


		}
	} catch (ex: IOException) {
		f.close()
	}
	val trans = TransformerFactory.newInstance().newTransformer()
	trans.setOutputProperty(OutputKeys.INDENT, "yes")
	trans.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4")
	trans.transform(DOMSource(doc), StreamResult("Rutes.xml"))
}