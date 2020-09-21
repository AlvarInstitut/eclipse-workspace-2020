package exercicis

import javax.xml.parsers.DocumentBuilderFactory
import org.w3c.dom.Element
import org.json.JSONObject
import org.json.JSONArray
import java.io.FileWriter

fun main(args: Array<String>) {
	val doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse("cotxes.xml")
	val vehicles = doc.getElementsByTagName("vehiculo")
	val arrelJSON =JSONObject()
	val ofertaJSON = JSONObject()
	arrelJSON.put("oferta",ofertaJSON)
	val vehiclesJSON = JSONArray()
	ofertaJSON.put("vehiculos",vehiclesJSON)
	for (i in 0 until vehicles.getLength()){
		val v = vehicles.item(i) as Element
		val vJSON = JSONObject()
		vehiclesJSON.put(vJSON)
		vJSON.put("marca",v.getElementsByTagName("marca").item(0).getTextContent())
		val model = JSONObject()
		model.put("color",(v.getElementsByTagName("modelo").item(0) as Element).getAttribute("color"))
		model.put("nombre_modelo",v.getElementsByTagName("modelo").item(0).getTextContent())
		vJSON.put("modelo",model)
		val motorJSON = JSONObject()
		motorJSON.put("combustible",(v.getElementsByTagName("motor").item(0) as Element).getAttribute("combustible"))
		motorJSON.put("nombre_motor",v.getElementsByTagName("motor").item(0).getTextContent())
		vJSON.put("motor",motorJSON)
		vJSON.put("matricula",v.getElementsByTagName("matricula").item(0).getTextContent())
		vJSON.put("kilometros",v.getElementsByTagName("kilometros").item(0).getTextContent())
		vJSON.put("precio_inicial",v.getElementsByTagName("precio_inicial").item(0).getTextContent())
		vJSON.put("precio_oferta",v.getElementsByTagName("precio_oferta").item(0).getTextContent())
		val extraJSON = JSONArray()
		vJSON.put("extra",extraJSON)
		val extra = v.getElementsByTagName("extra")
		for(j in 0 until extra.getLength()){
			val e = extra.item(j) as Element
			val eJSON = JSONObject()
			eJSON.put("valor",e.getAttribute("valor"))
			eJSON.put("nombre_extra",e.getTextContent())
			extraJSON.put(eJSON)
		}
		val fotoJSON = JSONArray()
		vJSON.put("foto",fotoJSON)
		val foto = v.getElementsByTagName("foto")
		for(j in 0 until foto.getLength()){
			val f = foto.item(j) as Element
			fotoJSON.put(f.getTextContent())
		}
	}
	
	val f = FileWriter("cotxes.json")
	f.write(arrelJSON.toString(4))
	f.close()
}